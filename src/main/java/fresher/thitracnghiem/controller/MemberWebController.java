package fresher.thitracnghiem.controller;

import fresher.thitracnghiem.model.TestDTO;
import fresher.thitracnghiem.model.UserDoingTestDTO;
import fresher.thitracnghiem.service.TestService;
import fresher.thitracnghiem.service.UserDoingTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MemberWebController {

    private final TestService testService;
    private final UserDoingTestService doingTestService;

    @GetMapping("/homepage")
    public String homepage() {
        return "member/home";
    }

    @GetMapping("/de-thi")
    public String deThi() {
        return "member/de-thi";
    }

    @GetMapping("/doing-test/{id}")
    public String doingTest(Model model, @PathVariable("id") Long id) {

        TestDTO testDTO = testService.getOne(id);
        model.addAttribute("testDTO", testDTO);
        return "member/doing-test";
    }

    @GetMapping("/result/{id}")
    public String result(Model model, @PathVariable("id") Long id) {

        UserDoingTestDTO userDoingTestDTO = doingTestService.getOne(id);
        model.addAttribute("userDoingTestDTO", userDoingTestDTO);
        return "member/result";
    }
}
