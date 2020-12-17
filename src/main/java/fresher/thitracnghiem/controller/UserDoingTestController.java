package fresher.thitracnghiem.controller;

import fresher.thitracnghiem.model.CategoryDTO;
import fresher.thitracnghiem.model.QuestionDTO;
import fresher.thitracnghiem.model.TestDTO;
import fresher.thitracnghiem.service.CategoryService;
import fresher.thitracnghiem.service.QuestionService;
import fresher.thitracnghiem.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/userdoingtest")
@RequiredArgsConstructor
public class UserDoingTestController {

    private final TestService testService;
    private final CategoryService categoryService;
    private final QuestionService questionService;

    @GetMapping("/{id}")
    public String doingTest(Model model, @PathVariable Long id){
        TestDTO testDTO = testService.getOne(id);
        CategoryDTO categoryDTO = categoryService.getOne(testDTO.getCategory_id());

        List<QuestionDTO> listQuestionDTO = new ArrayList<QuestionDTO>();
        for (int i=0;i<testDTO.getListQuestionId().length;i++){
            QuestionDTO questionDTO = questionService.getOne(testDTO.getListQuestionId()[i]);
            listQuestionDTO.add(questionDTO);
        }

        model.addAttribute("test",testDTO);
        model.addAttribute("category",categoryDTO);
        model.addAttribute("list",listQuestionDTO);
        return "userdoingtest/doing-test";
    }
}
