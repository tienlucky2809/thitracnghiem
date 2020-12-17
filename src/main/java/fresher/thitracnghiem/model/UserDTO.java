package fresher.thitracnghiem.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long userId;
    private String userName;
    private String encrytedPassword;
    private Boolean enabled;


}
