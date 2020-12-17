package fresher.thitracnghiem.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionSearchDTO extends SearchDTO {

    private Long id;
    private String content;
    private int level;
    private Boolean enabled;
    private int point;
    private Long categoryId;
    private Long questionTypeId;
}
