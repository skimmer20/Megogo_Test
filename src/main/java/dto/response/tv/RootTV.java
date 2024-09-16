package dto.response.tv;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author Yurii Pavliuk
 */
@Data
public class RootTV {
    private String result;
    private ArrayList<DataTV> data;
}
