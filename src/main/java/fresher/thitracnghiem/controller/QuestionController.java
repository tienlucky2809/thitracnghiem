package fresher.thitracnghiem.controller;

import fresher.thitracnghiem.model.CategoryDTO;
import fresher.thitracnghiem.model.QuestionDTO;
import fresher.thitracnghiem.model.QuestionTypeDTO;
import fresher.thitracnghiem.service.CategoryService;
import fresher.thitracnghiem.service.QuestionService;
import fresher.thitracnghiem.service.QuestionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final CategoryService categoryService;
    private final QuestionTypeService questionTypeService;

    @GetMapping("/add")
    public String add(Model model) {
        List<CategoryDTO> categoryDTOS = categoryService.getAll();
        if (questionTypeService.getAll() == null) {
            questionTypeService.add(new QuestionTypeDTO("True - False", 1, true));
            questionTypeService.add(new QuestionTypeDTO("Multi Choice", 2, true));
            questionTypeService.add(new QuestionTypeDTO("Writing", 3, true));
        }
        List<QuestionTypeDTO> questionTypeDTOS = questionTypeService.getAll();
        model.addAttribute("categoryDTOS", categoryDTOS);
        model.addAttribute("questionTypeDTOS", questionTypeDTOS);
        return "admin/question/add";
    }

    @GetMapping("/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        QuestionDTO questionDTO = questionService.getOne(id);
        CategoryDTO categorySubDTO = categoryService.getOne(questionDTO.getCategoryId());
        List<CategoryDTO> categoryDTOS = categoryService.getAll()
                .stream()
                .filter(categoryDTO -> categoryDTO.getId() != categorySubDTO.getCategoryParentId())
                .collect(Collectors.toList());
        List<QuestionTypeDTO> questionTypeDTOS = questionTypeService.getAll()
                .stream()
                .filter(questionTypeDTO -> questionTypeDTO.getValue() != questionDTO.getQuestionTypeValue())
                .collect(Collectors.toList());

        model.addAttribute("categoryDTO", categorySubDTO);
        model.addAttribute("questionDTO", questionDTO);
        model.addAttribute("questionTypeDTOS", questionTypeDTOS);
        model.addAttribute("categoryDTOS", categoryDTOS);

        return "admin/question/update";
    }

    @GetMapping
    public String getAll(Model model) {
        List<QuestionDTO> questionDTOS = questionService.getAll();
        model.addAttribute("questionDTOS", questionDTOS);
        return "admin/question/list";
    }

    @GetMapping("/import-file")
    public String addQuestionByFile() {
        return "admin/question/import";
    }
}
