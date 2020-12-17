package fresher.thitracnghiem.dao;

import fresher.thitracnghiem.entity.Category;
import fresher.thitracnghiem.entity.User;
import fresher.thitracnghiem.model.CategorySearchDTO;
import fresher.thitracnghiem.model.UserDTO;

import java.util.List;


public interface UserRegisDAO {
    User add(User user);
    List<User> getAll();
    List<User> search(UserDTO userDTO);

    User getOne(long id);
}
