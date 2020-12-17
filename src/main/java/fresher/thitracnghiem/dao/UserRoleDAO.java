package fresher.thitracnghiem.dao;

import fresher.thitracnghiem.entity.UserRole;

import java.util.List;

public interface UserRoleDAO {
    UserRole add(UserRole userRole);
    void update(UserRole userRole);
    List<UserRole> getAll();

}
