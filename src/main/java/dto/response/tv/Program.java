package dto.response.tv;

import lombok.Data;

/**
 * @author Yurii Pavliuk
 */
@Data
public class Program {
    private int external_id;
    private String title;
    private Category category;
    private String season;
    private String episode;
    private Pictures pictures;
    private int start_timestamp;
    private int end_timestamp;
    private int id;
    private String start;
    private String end;
    private String virtual_object_id;
    private String schedule_type;
}
