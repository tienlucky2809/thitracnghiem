package fresher.thitracnghiem.api.controller;

import fresher.thitracnghiem.entity.Answer;
import fresher.thitracnghiem.entity.UserDoingTest;
import fresher.thitracnghiem.model.*;
import fresher.thitracnghiem.service.TestService;
import fresher.thitracnghiem.service.UserDoingTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/userdoingtest")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = -1)
public class UserDoingTestApiController {
    private final TestService testService;
    private final UserDoingTestService userDoingTestService;
    private final UserDoingTestService doingTestService;

    //
    @GetMapping("/listtest/{id}")
    public ResponseEntity getTestByCategory(@PathVariable("id") Long id) {
        List<TestDTO> listTestDTO = new ArrayList<TestDTO>();
        listTestDTO = testService.getTestByCategory(id);
        return ResponseEntity.ok(listTestDTO);
    }


    //
    @GetMapping("/listquestion/{id}")
    public ResponseEntity getQuesionByTest(@PathVariable("id") Long id){
        TestDTO testDTO = testService.getOne(id);
        return ResponseEntity.ok(testDTO.getQuestionDTOS());
    }

    //
    @GetMapping("/listanswer/{id}")
    public ResponseEntity getAnswerByTest(@PathVariable("id") Long id){
        TestDTO testDTO = testService.getOne(id);
        List<QuestionDTO> listQuestionDTO= testDTO.getQuestionDTOS();
        List<AnswerDTO> listAnswerDTO = new ArrayList<>();
        for(QuestionDTO questionDTO : listQuestionDTO){
            for(AnswerDTO answerDTO : questionDTO.getAnswerDTOS()){
                listAnswerDTO.add(answerDTO);
            }
        }
        return ResponseEntity.ok(listAnswerDTO);
    }



    @PostMapping()
    public ResponseEntity addUserDoingTest(@PathVariable(name = "user_id") Long userId,
                                            @PathVariable(name = "test_id") Long testId,
                                            @PathVariable(name = "point") Integer point){
        UserDoingTestDTO userDoingTestDTO = new UserDoingTestDTO();
        userDoingTestDTO.setUser_id(userId);
        userDoingTestDTO.setTest_id(testId);
        userDoingTestDTO.setPoint(point);
        userDoingTestService.add(userDoingTestDTO);

        return ResponseEntity.ok().body(userDoingTestDTO);
    }
    //    @PostMapping()
    //    public ResponseEntity addUserDoingQuestion(@PathVariable(name = "userDoingTest_id") Long userDoingTest_id,
    //                                           @PathVariable(name = "test_id") Long testId,
    //                                           @PathVariable(name = "point") Integer point){
    //        UserDoingTestDTO userDoingTestDTO = new UserDoingTestDTO();
    //        userDoingTestDTO.setUser_id(userId);
    //        userDoingTestDTO.setTest_id(testId);
    //        userDoingTestDTO.setPoint(point);
    //        userDoingTestService.add(userDoingTestDTO);
    //
    //        return ResponseEntity.ok().body(userDoingTestDTO);
    //    }

    @GetMapping("/result/{id}")
    public ResponseEntity getUserDoingTest(@PathVariable("id") Long id){
        UserDoingTestDTO userDoingTestDTO = userDoingTestService.getOne(id);
        return ResponseEntity.ok(userDoingTestDTO);
    }

    @PostMapping("/submit")
    public UserDoingTestDTO submit(@RequestBody List<UserDoingQuesionDTO> userDoingQuesionDTOS, Principal principal) {
       return doingTestService.submit(userDoingQuesionDTOS);
    }


}
