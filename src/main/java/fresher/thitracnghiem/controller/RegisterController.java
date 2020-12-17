package fresher.thitracnghiem.controller;

import fresher.thitracnghiem.model.UserDTO;
import fresher.thitracnghiem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor


public class RegisterController {
    @Autowired
    private final UserService userService;


    @GetMapping
    public String register(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "register";
    }


    @PostMapping
    public String add(Model model, @ModelAttribute("userDTO")UserDTO userDTO){

        userDTO.setEnabled(true);
        userService.register(userDTO);
        return "redirect:/login";

    }

}
