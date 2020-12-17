package fresher.thitracnghiem.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class UserDoingQuesionDTO {

    private long id;

    private long userDoingTest_id;
    private List<String> content;
    private long question_id;

    private Integer point;
    private Boolean isPass;
    private String createdDate;
    private String lastModifiedDate;

}
