package fresher.thitracnghiem.controller;

import fresher.thitracnghiem.model.CategoryDTO;
import fresher.thitracnghiem.model.CategorySearchDTO;
import fresher.thitracnghiem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("categoryDTO", new CategoryDTO());
        return "admin/category/add";
    }

    @GetMapping("/sub/add")
    public String addSub(Model model) {
        CategoryDTO categoryDTO = new CategoryDTO();
        List<CategoryDTO> categoryDTOS = categoryService.getAll();
        model.addAttribute("categoriesParent", categoryDTOS);
        model.addAttribute("categoryDTO", categoryDTO);
        return "admin/sub-category/add";
    }

    @PostMapping("/add")
    public String add(Model model, @ModelAttribute("categoryDTO") CategoryDTO categoryDTO) {
        categoryDTO.setEnabled(true);
        categoryService.add(categoryDTO);
        return "redirect:/admin/category";
    }

    @PostMapping("/sub/add")
    public String addSub(Model model, @ModelAttribute("categoryDTO") CategoryDTO categoryDTO) {
        categoryDTO.setEnabled(true);
        categoryService.add(categoryDTO);
        return "redirect:/admin/category/sub";
    }

    @GetMapping("/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        CategoryDTO categoryDTO = categoryService.getOne(id);
        model.addAttribute("categoryDTO", categoryDTO);
        return "admin/category/update";
    }

    @PostMapping()
    public String update(@ModelAttribute CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
        return "redirect:/admin/category";
    }

    @GetMapping("/sub/{id}")
    public String updateSub(Model model, @PathVariable("id") Long id) {
        CategoryDTO current = categoryService.getOne(id);
        List<CategoryDTO> categoryDTOS = categoryService.getAll()
                .stream()
                .filter(categoryDTO -> categoryDTO.getId() != categoryDTO.getCategoryParentId())
                .collect(Collectors.toList());
        model.addAttribute("categoriesParent", categoryDTOS);
        model.addAttribute("categoryDTO", current);
        return "admin/sub-category/update";
    }

    @PostMapping("/sub")
    public String updateSub(@ModelAttribute CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
        return "redirect:/admin/category/sub";
    }

    @GetMapping
    public String getAll(Model model) {
        CategorySearchDTO searchDTO = new CategorySearchDTO();
        searchDTO.setIsSub(false);
        searchDTO.setEnabled(true);
        List<CategoryDTO> categoryDTOS = categoryService.search(searchDTO);
        model.addAttribute("categories", categoryDTOS);
        return "admin/category/list";
    }

    @GetMapping("/sub")
    public String getAllSub(Model model) {
        CategorySearchDTO searchDTO = new CategorySearchDTO();
        searchDTO.setIsSub(true);
        searchDTO.setEnabled(true);
        List<CategoryDTO> categoryDTOS = categoryService.search(searchDTO);
        model.addAttribute("categories", categoryDTOS);
        return "admin/sub-category/list";
    }
}
