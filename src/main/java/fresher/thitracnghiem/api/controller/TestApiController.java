package fresher.thitracnghiem.api.controller;

import fresher.thitracnghiem.model.TestDTO;
import fresher.thitracnghiem.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = -1)
public class TestApiController {

    private final TestService testService;

    @PostMapping
    public ResponseEntity add(@RequestBody TestDTO testDTO) {
        testService.add(testDTO);
        return ResponseEntity.ok(testDTO);
    }

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(testService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(testService.getOne(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        Boolean b = testService.delete(id);
        return ResponseEntity.ok(b);
    }
}
