package dto.response.tv;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Yurii Pavliuk
 */
@Data
public class Pictures {
    private String original;
    @JsonProperty("105x85")
    private String _105x85;
    private String monochrome_logo;
    @JsonProperty("180x180")
    private String _180x180;
    @JsonProperty("980x560")
    private String _980x560;
    @JsonProperty("88x65")
    private String _88x65;
    @JsonProperty("88x88")
    private String _88x88;
    @JsonProperty("105x105")
    private String _105x105;
    @JsonProperty("40x40")
    private String _40x40;
    @JsonProperty("150x150")
    private String _150x150;
    @JsonProperty("1600x520")
    private String _1600x520;
    @JsonProperty("150x85")
    private String _150x85;
    @JsonProperty("105x60")
    private String _105x60;
    @JsonProperty("125x70")
    private String _125x70;
    @JsonProperty("480x330")
    private String _480x330;
    @JsonProperty("230x130")
    private String _230x130;
}
