package fresher.thitracnghiem.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserStringDTO {
    Long id;
    String userName;
    String roleName;
    private Boolean enabled;
}
