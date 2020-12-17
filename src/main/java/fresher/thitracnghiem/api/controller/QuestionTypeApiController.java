package fresher.thitracnghiem.api.controller;

import fresher.thitracnghiem.model.QuestionTypeDTO;
import fresher.thitracnghiem.service.AnswerService;
import fresher.thitracnghiem.service.QuestionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/question-type")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = -1)
public class QuestionTypeApiController {

    private final QuestionTypeService questionTypeService;
    private final AnswerService answerService;
    
    @PostMapping
    public ResponseEntity add(@RequestBody QuestionTypeDTO questionTypeDTO) {
        questionTypeService.add(questionTypeDTO);
        return ResponseEntity.ok(questionTypeDTO);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody QuestionTypeDTO questionTypeDTO) {
        questionTypeService.update(questionTypeDTO);
        return ResponseEntity.ok(questionTypeDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        questionTypeService.delete(id);
    }

    @GetMapping
    public ResponseEntity getAll() {
        Optional<List<QuestionTypeDTO>> questionTypeDTOs = Optional.of(questionTypeService.getAll());
        if(questionTypeDTOs.isPresent()){
            return ResponseEntity.ok(questionTypeDTOs);
        } else return ResponseEntity.notFound().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        Optional<QuestionTypeDTO> questionTypeDTO = Optional.of(questionTypeService.getOne(id));
        if(questionTypeDTO.isPresent()){
            return ResponseEntity.ok(questionTypeDTO);
        } else return ResponseEntity.notFound().build();
    }
    
}
