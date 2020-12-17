package fresher.thitracnghiem.service;

import fresher.thitracnghiem.entity.test.Test;
import fresher.thitracnghiem.model.*;

import java.util.List;

public interface TestService {

    TestDTO add(TestDTO testDTO);
    TestDTO getOne(Long id);
    List<TestDTO> getAll();
    List<TestDTO> getTestByCategory(Long id);

    TestDTO update(TestDTO testDTO);
    Boolean delete(Long id);

    List<TestStatisticDTO> statisticByTest();

    List<UserStatisticDTO> statisticByUsers();

    List<QuestionStatisticDTO> statisticByQuestions(String fromDate, String toDate);

    List<TestsByUsersDTO> statisticTestsByUsers(String fromDate, String toDate);

    List<CheckStatusTestDTO> getAllTestPassAndFailed(String fromDate, String toDate);

}
