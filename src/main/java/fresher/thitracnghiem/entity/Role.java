package fresher.thitracnghiem.entity;

import javax.persistence.*;

@Entity
@Table(name = "app_role", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "app_role_uk", columnNames = "role_name") })
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role_name", length = 30, nullable = false)
    private String roleName;

    public Role() {
    }

    public Role(Long idRole) {
        this.roleId= idRole;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}