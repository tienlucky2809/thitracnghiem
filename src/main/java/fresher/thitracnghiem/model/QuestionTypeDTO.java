package fresher.thitracnghiem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionTypeDTO {

    public QuestionTypeDTO(String name, int value, Boolean enabled) {
        this.name = name;
        this.value = value;
        this.enabled = enabled;
    }

    private Long id;
    private String name;
    private int value;
    private Boolean enabled;
    private String createdDate;
    private String lastModifiedDate;

}
