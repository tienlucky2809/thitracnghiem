package fresher.thitracnghiem.resource;

import fresher.thitracnghiem.model.*;
import fresher.thitracnghiem.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestStatisticResource {

    @Autowired
    private TestService testService;

    @GetMapping("/statistic/tests")
    public List<TestStatisticDTO> statisticTest() {
        return testService.statisticByTest();
    }

    @GetMapping("/statistic/users")
    public List<UserStatisticDTO> reportUsersByEnabled() {
        return testService.statisticByUsers();
    }

    @GetMapping("/statistic/questions")
    public List<QuestionStatisticDTO> statisticQuestion(@RequestParam(value = "fromDate") String fromDate,
                                                        @RequestParam(value = "toDate") String toDate) {
        return testService.statisticByQuestions(fromDate, toDate);
    }

    @GetMapping("/statistic/test-by-users")
    public List<TestsByUsersDTO> statisticTestByUsers(@RequestParam(value = "fromDate") String fromDate,
                                                      @RequestParam(value = "toDate") String toDate) {
        return testService.statisticTestsByUsers(fromDate, toDate);
    }

    @GetMapping("statistic/all-status-tests")
    public List<CheckStatusTestDTO> getAllStatusTest(@RequestParam(value = "fromDate") String fromDate,
                                                     @RequestParam(value = "toDate") String toDate) {
        return testService.getAllTestPassAndFailed(fromDate, toDate);
    }
}
