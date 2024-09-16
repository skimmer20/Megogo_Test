package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author Yurii Pavliuk
 */
public class RequestUtils {

    public static final String BASE_URL = "https://epg.megogo.net";

    public static String getUrl(String endpoint, Map<String, Object> queryParams) throws UnsupportedEncodingException {
        StringBuilder builder = new StringBuilder(BASE_URL).append(endpoint).append("?");

        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            builder.append(entry.getKey())
                    .append('=')
                    .append(URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8")
                            .replace("+", "%20"))
                    .append('&');
        }

        String url = builder.toString();
        return url.endsWith("&") ? url.substring(0, url.length() - 1) : url;
    }
}
