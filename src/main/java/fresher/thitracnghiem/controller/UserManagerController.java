package fresher.thitracnghiem.controller;


import fresher.thitracnghiem.model.CategoryDTO;
import fresher.thitracnghiem.model.CategorySearchDTO;
import fresher.thitracnghiem.model.UserDTO;
import fresher.thitracnghiem.model.UserStringDTO;
import fresher.thitracnghiem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/usermanager")
@RequiredArgsConstructor
public class UserManagerController {

    private final UserService userService;
    @GetMapping("/list")
    public String getAll(Model model) {
        UserDTO searchDTO = new UserDTO();
        searchDTO.setEnabled(true);
        List<UserDTO> userDTOS = userService.search(searchDTO);
        model.addAttribute("users", userDTOS);
        return "usermanager";
    }

//    @GetMapping("/list")
//    public String getAll(Model model) {
//        UserStringDTO searchStringDTO = new UserStringDTO();
//        searchStringDTO.setEnabled(true);
//        List<UserStringDTO> userStringDTOS = userService.findUser(searchStringDTO);
//        model.addAttribute("users", userStringDTOS);
//        return "usermanager";
//    }

    @GetMapping("/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        UserDTO userDTO = userService.getOne(id);
        model.addAttribute("userDTO", userDTO);
        return "userupdate"; // copy update.html
    }

    @PostMapping()
    public String update(@ModelAttribute UserDTO userDTO) {
        userService.update(userDTO);
        return "redirect:/admin/category";
    }
}
