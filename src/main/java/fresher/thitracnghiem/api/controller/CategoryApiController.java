package fresher.thitracnghiem.api.controller;

import fresher.thitracnghiem.model.CategoryDTO;
import fresher.thitracnghiem.model.CategorySearchDTO;
import fresher.thitracnghiem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/category")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = -1)
public class CategoryApiController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity add(@RequestBody CategoryDTO categoryDTO) {
        categoryService.add(categoryDTO);
        return ResponseEntity.ok(categoryDTO);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
        return ResponseEntity.ok(categoryDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        categoryService.disable(id);
    }

    @PostMapping("/list")
    public ResponseEntity getAllCategory(@RequestBody CategorySearchDTO categorySearchDTO) {
        Optional<List<CategoryDTO>> categoryDTOs = Optional.of(categoryService.search(categorySearchDTO));
        if (categoryDTOs.isPresent()) {
            return ResponseEntity.ok(categoryDTOs);
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping("/sub")
    public ResponseEntity getAllSubCategoryByParent(@RequestBody CategorySearchDTO categorySearchDTO) {
        Optional<List<CategoryDTO>> categoryDTOs = Optional.of(categoryService.search(categorySearchDTO));
        if (categoryDTOs.isPresent()) {
            return ResponseEntity.ok(categoryDTOs);
        } else return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        Optional<CategoryDTO> categoryDTO = Optional.of(categoryService.getOne(id));
        if (categoryDTO.isPresent()) {
            return ResponseEntity.ok(categoryDTO);
        } else return ResponseEntity.notFound().build();
    }

}
