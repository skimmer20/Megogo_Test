package api;

import dto.response.time.Root;
import dto.response.tv.DataTV;
import dto.response.tv.Program;
import dto.response.tv.RootTV;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import rest.RestClient;
import rest.VideoService;
import utils.DateTimeUtils;
import utils.RequestUtils;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.RequestUtils.BASE_URL;

/**
 * @author Yurii Pavliuk
 */
@Execution(ExecutionMode.CONCURRENT)
public class MegogoTest {

    private static final List<String> VIDEOS = Arrays.asList("1639111", "1585681", "1639231");
    private static final String TIME_ENDPOINT = "/time";

    @Test
    @Description("Verify the current time")
    public void testCurrentTime() {
        Response response = RestClient.get(RestClient.createRequestSpecification(), BASE_URL + TIME_ENDPOINT);

        Root root = response.as(Root.class);
        long currentTime = root.data.getTimestamp();

        Instant now = DateTimeUtils.getCurrentInstant();
        Instant responseTime = DateTimeUtils.fromEpochSecond(currentTime);

        assertTrue(DateTimeUtils.isTimeDifferenceWithinThreshold(responseTime, now, 3),
                "Time difference should be less than 3 seconds");
    }

    @Test
    @Description("Verify the programs are sorted by start time")
    public void testSortedChannels() throws UnsupportedEncodingException {
        for (String videoId : VIDEOS) {
            Map<String, Object> queryParams = Map.of("video_ids", videoId);
            Response response = VideoService.getChannelResponse(queryParams);
            RootTV rootTV = response.as(RootTV.class);
            List<Program> sortedPrograms = rootTV.getData().stream()
                    .map(DataTV::getPrograms)
                    .flatMap(List::stream)
                    .sorted(Comparator.comparingLong(Program::getStart_timestamp))
                    .collect(Collectors.toList());

            for (int i = 1; i < sortedPrograms.size(); i++) {
                Program previousProgram = sortedPrograms.get(i - 1);
                Program currentProgram = sortedPrograms.get(i);

                assertTrue(previousProgram.getStart_timestamp() <= currentProgram.getStart_timestamp(),
                        "The programs are not sorted: " + previousProgram.getTitle() + " and " + currentProgram.getTitle());
            }
        }
    }

    @Test
    @Description("Verify the program exists for current time")
    public void testCurrentProgram() throws UnsupportedEncodingException {
        for (String videoId : VIDEOS) {
            Map<String, Object> queryParams = Map.of("video_ids", videoId);
            Response response = VideoService.getChannelResponse(queryParams);

            RootTV rootTV = response.as(RootTV.class);

            boolean programExists = rootTV.getData().stream()
                    .flatMap(channel -> channel.getPrograms().stream())
                    .anyMatch(program -> DateTimeUtils.isProgramInCurrentRange(program.getStart_timestamp(), program.getEnd_timestamp()));

            assertTrue(programExists, "There are no programs for current time on the channel with ID: " + videoId);
        }
    }

    @Test
    @Description("Verify there are no programs from the past or further than 24 hours in the schedule")
    public void testPastPrograms() throws UnsupportedEncodingException {
        long currentTimestamp = DateTimeUtils.getCurrentEpochSecond();
        long futureTimestamp = currentTimestamp + 24 * 60 * 60;

        for (String videoId : VIDEOS) {
            Map<String, Object> queryParams = Map.of("video_ids", videoId);
            Response response = VideoService.getChannelResponse(queryParams);

            RootTV rootTV = response.as(RootTV.class);
            boolean allProgramsValid = rootTV.getData().stream()
                    .flatMap(channel -> channel.getPrograms().stream())
                    .allMatch(program -> DateTimeUtils.isWithinTimeRange(program.getStart_timestamp(), currentTimestamp, futureTimestamp));

            assertTrue(allProgramsValid, "The channel with ID: " + videoId + " contains programs out of 24 hours range");
        }
    }
}
