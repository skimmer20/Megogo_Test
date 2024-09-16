package rest;

import io.restassured.response.Response;
import utils.RequestUtils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author Yurii Pavliuk
 */
public class VideoService {

    private static final String TIME_ENDPOINT = "/time";
    private static final String CHANNEL_ENDPOINT = "/channel";

    public static Response getChannelResponse(Map<String, Object> queryParams) throws UnsupportedEncodingException {
        String url = RequestUtils.getUrl(CHANNEL_ENDPOINT, queryParams);
        return RestClient.get(RestClient.createRequestSpecification(), url);
    }
}
