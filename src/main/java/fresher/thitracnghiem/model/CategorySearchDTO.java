package fresher.thitracnghiem.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategorySearchDTO extends SearchDTO {

    private String name;
    private Boolean enabled;
    private Long categoryParentId;
    private Boolean isSub;
}
