package fresher.thitracnghiem.model;

import fresher.thitracnghiem.entity.Role;
import fresher.thitracnghiem.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleDTO {
    private Long id;
    private User user;
    private Role role;
}
