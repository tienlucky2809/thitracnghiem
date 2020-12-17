package fresher.thitracnghiem.service;

import fresher.thitracnghiem.model.CategoryDTO;
import fresher.thitracnghiem.model.CategorySearchDTO;
import fresher.thitracnghiem.model.UserDTO;
import fresher.thitracnghiem.model.UserStringDTO;

import java.util.List;

public interface UserService {
    UserDTO add(UserDTO userDTO);
    UserDTO register(UserDTO userDTO);
    List<UserDTO> getAll();

    List<UserDTO> search(UserDTO UserDTO);
    List<UserStringDTO> findUser(UserStringDTO UserStringDTO);

   // List<UserStringDTO> getAll();

    UserDTO getOne(long id);

    UserDTO update(UserDTO userDTO);
}
