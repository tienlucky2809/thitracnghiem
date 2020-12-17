package fresher.thitracnghiem.api.controller;

import fresher.thitracnghiem.model.AnswerDTO;
import fresher.thitracnghiem.model.QuestionDTO;
import fresher.thitracnghiem.model.QuestionSearchDTO;
import fresher.thitracnghiem.service.AnswerService;
import fresher.thitracnghiem.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/question")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = -1)
public class QuestionApiController {


    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity add(@RequestBody QuestionDTO questionDTO) {
        QuestionDTO dto = questionService.add(questionDTO);
        for (AnswerDTO answerDTO : questionDTO.getAnswerDTOS()
        ) {
            answerDTO.setQuestionId(dto.getId());
            answerService.add(answerDTO);
        }
        return ResponseEntity.ok(questionDTO);
    }

    @PostMapping("/byfile")
    public void addByFile(@RequestBody List<QuestionDTO> questionDTOS) {

        List<QuestionDTO> dtos = questionService.addAll(questionDTOS);
        for (QuestionDTO questionDTO : dtos
        ) {
            for (AnswerDTO answerDTO : questionDTO.getAnswerDTOS()
            ) {
                answerDTO.setQuestionId(questionDTO.getId());
                answerService.add(answerDTO);
            }
        }

    }

    @PutMapping
    public ResponseEntity update(@RequestBody QuestionDTO questionDTO) {
        questionService.update(questionDTO);
        for (AnswerDTO answerDTO : questionDTO.getAnswerDTOS()
        ) {
            answerDTO.setQuestionId(questionDTO.getId());
            answerService.add(answerDTO);
        }
        return ResponseEntity.ok(questionDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        questionService.disable(id);
    }

    @GetMapping
    public ResponseEntity getAll() {
        Optional<List<QuestionDTO>> questionDTOs = Optional.of(questionService.getAll());
        if (questionDTOs.isPresent()) {
            return ResponseEntity.ok(questionDTOs);
        } else return ResponseEntity.notFound().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        Optional<QuestionDTO> questionDTO = Optional.of(questionService.getOne(id));
        if (questionDTO.isPresent()) {
            return ResponseEntity.ok(questionDTO);
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping("/search")
    public ResponseEntity search(@RequestBody QuestionSearchDTO questionSearchDTO) {
        Optional<List<QuestionDTO>> questionDTOs = Optional.of(questionService.search(questionSearchDTO));
        if (questionDTOs.isPresent()) {
            return ResponseEntity.ok(questionDTOs);
        } else return ResponseEntity.notFound().build();

    }
}
