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
@RequestMapping("/admin/test")
@RequiredArgsConstructor
public class TestController {


    private final TestService testService;
    private final CategoryService categoryService;
    private final QuestionService questionService;

    @GetMapping
    public String getAll(Model model){
        List<TestDTO> list = testService.getAll();
        model.addAttribute("listTest",list);
        return "admin/test/list";
    }

    @GetMapping("/{id}")
    public String details_test(Model model,@PathVariable Long id){
        TestDTO testDTO = testService.getOne(id);
        CategoryDTO categoryDTO = categoryService.getOne(testDTO.getCategory_id());
        List<QuestionDTO> list = new ArrayList<>();
        for (int i=0;i<testDTO.getListQuestionId().length;i++){
            QuestionDTO questionDTO = questionService.getOne(testDTO.getListQuestionId()[i]);
            list.add(questionDTO);
        }
        model.addAttribute("test",testDTO);
        model.addAttribute("category",categoryDTO);
        model.addAttribute("list",list);
        return "admin/test/details";
    }

    @GetMapping("/add")
    public String add(Model model){
        List<CategoryDTO> list  = categoryService.getAll();
        model.addAttribute("listCategory",list);
        return "admin/test/add";
    }



}
