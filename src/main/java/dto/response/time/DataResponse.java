package dto.response.time;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Yurii Pavliuk
 */
@Data
public class DataResponse {
    @JsonProperty("utc_offset")
    private int utcOffset;
    @JsonProperty("timestamp_gmt")
    private int timestampGMT;
    @JsonProperty("timestamp_local")
    private int timestampLocal;
    private String timezone;
    private int timestamp;
    @JsonProperty("time_local")
    private String timeLocal;
}
