package fresher.thitracnghiem.model;

import lombok.Data;

@Data
public class UserDoingTestDTO {
    private Long id;
    private Long user_id;
    private Long test_id;
    private Integer point;
    private Boolean ispass;

    private String startTime;
    private String endTime;

    private String createdDate;
    private String lastModifiedDate;


}
