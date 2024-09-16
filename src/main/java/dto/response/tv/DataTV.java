package dto.response.tv;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author Yurii Pavliuk
 */
@Data
public class DataTV {
    public int id;
    public int external_id;
    public String title;
    public Pictures pictures;
    public int video_id;
    public ArrayList<Program> programs;
}
