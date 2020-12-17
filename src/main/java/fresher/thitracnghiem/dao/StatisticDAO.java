package fresher.thitracnghiem.dao;


import fresher.thitracnghiem.model.*;

import java.util.List;

public interface StatisticDAO{
    List<TestStatisticDTO> testsStatistic();

    List<UserStatisticDTO> usersStatistic();

    List<QuestionStatisticDTO> questionsStatistic(String fromDate, String toDate);

    List<TestsByUsersDTO> testByUsers(String fromDate, String toDate);

    List<CheckStatusTestDTO> getAllTest(String fromDate, String toDate);
}
