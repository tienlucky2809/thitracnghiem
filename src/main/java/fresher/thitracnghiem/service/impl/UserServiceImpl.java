package fresher.thitracnghiem.service.impl;

import fresher.thitracnghiem.dao.UserRegisDAO;
import fresher.thitracnghiem.dao.UserRoleDAO;
import fresher.thitracnghiem.entity.*;
import fresher.thitracnghiem.model.*;
import fresher.thitracnghiem.service.UserService;
import fresher.thitracnghiem.utils.EncrytedPasswordUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRegisDAO userRegisDAO;

    private final UserRoleDAO userRoleDAO;
    private final ModelMapper modelMapper;

    @Override
    public UserDTO add(UserDTO userDTO) {
        userDTO.setEnabled(true);

        userDTO.setEncrytedPassword(EncrytedPasswordUtils.encrytePassword(userDTO.getEncrytedPassword()));
        User user = userRegisDAO.add(convertToEntity(userDTO));
        return userDTO;
    }
    @Override
    public UserDTO register(UserDTO userDTO) {
        userDTO.setEnabled(true);

        userDTO.setEncrytedPassword(EncrytedPasswordUtils.encrytePassword(userDTO.getEncrytedPassword()));
        User user = userRegisDAO.add(convertToEntity(userDTO));
        userDTO.setUserId(user.getUserId());

        UserRoleDTO userRoleDTO =new UserRoleDTO();
        userRoleDTO.setUser(user);
        userRoleDTO.setRole(new Role(2l));
       userRoleDAO.add(convertToEntity(userRoleDTO));
        return userDTO;
    }

//    @Override
//    public List<UserStringDTO> getAll() {
//        List<UserStringDTO> userStringDTOS = new ArrayList<>();
//        List<UserDTO> userDTOS = new ArrayList<>();
//
//        List<User> users = userRegisDAO.getAll();
//        List<UserRole> userRoles = userRoleDAO.getAll();
//        for (User user : users
//        ) {
//            UserDTO userDTO = convertToDTO(user);
//            userDTOS.add(userDTO);
//        }
//        for (UserRole userRole : userRoles
//        ) {
//            UserStringDTO userStringDTO = convertToDTO(userRole);
//            userStringDTOS.add(userStringDTO);
//        }
//        return userStringDTOS;
//    }

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRegisDAO.getAll();
        for (User user : users
        ) {
            UserDTO userDTO = convertToDTO(user);
            userDTOS.add(userDTO);
        }
        // loc ra cac category theo dieu kien
        return userDTOS.stream().filter(userDTO -> userDTO.getEnabled()== true).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> search(UserDTO UserDTO) {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRegisDAO.search(UserDTO);
        for (User user : users
        ) {
            UserDTO userDTO = convertToDTO(user);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public List<UserStringDTO> findUser(UserStringDTO UserStringDTO) {
        List<UserStringDTO> userStringDTOS = new ArrayList<>();
        List<UserRole> userRoles = userRoleDAO.getAll();
        for (UserRole userRole : userRoles
        ) {
            UserStringDTO userStringDTO = convertToDTO(userRole);
            userStringDTOS.add(userStringDTO);
        }
        return userStringDTOS;
    }

    @Override
    public UserDTO getOne(long id) {
        return null;
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return null;
    }


    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }


    private User convertToEntity(UserDTO userDTO) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        User user = modelMapper.map(userDTO, User.class);

        return user;
    }


    private UserRole convertToEntity(UserRoleDTO userRoleDTO) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        UserRole userRole = modelMapper.map(userRoleDTO, UserRole.class);

        return userRole;
    }


    private UserStringDTO convertToDTO(UserRole userRole) {
        UserStringDTO userStringDTO = modelMapper.map(userRole, UserStringDTO.class);
        return userStringDTO;
    }


    private UserRole convertToEntity(UserStringDTO userStringDTO) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        UserRole userRole = modelMapper.map(userStringDTO, UserRole.class);

        return userRole;
    }


}
