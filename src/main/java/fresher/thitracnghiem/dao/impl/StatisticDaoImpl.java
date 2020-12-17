package fresher.thitracnghiem.dao.impl;

import fresher.thitracnghiem.dao.StatisticDAO;
import fresher.thitracnghiem.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StatisticDaoImpl implements StatisticDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /***
     * Statistic the number of tests for each category
     * @return
     */
    @Override
    public List<TestStatisticDTO> testsStatistic() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT testName.NAME name ");
        stringBuilder.append(", COUNT(cate.id) totalTests ");
        stringBuilder.append("FROM test testName ");
        stringBuilder.append("JOIN category cate ON testName.category_id = cate.ID ");
        stringBuilder.append("GROUP BY testName.NAME");


        List<TestStatisticDTO> data = jdbcTemplate.query(stringBuilder.toString(), new TestsStatisticMapper());
        return data;
    }

    class TestsStatisticMapper implements RowMapper<TestStatisticDTO> {

        @Nullable
        @Override
        public TestStatisticDTO mapRow(ResultSet resultSet, int i) throws SQLException {
            TestStatisticDTO testStatisticDTO = new TestStatisticDTO();
            testStatisticDTO.setName(resultSet.getString("name"));
            testStatisticDTO.setTotalTests(resultSet.getInt("totalTests"));
            return testStatisticDTO;
        }
    }

    /**
     * Statistic How many people has been enabled or not
     *
     * @return
     */
    @Override
    public List<UserStatisticDTO> usersStatistic() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT count(user_id) as numberPerson, enabled ");
        stringBuilder.append("FROM user ");
        stringBuilder.append("GROUP BY enabled");

        List<UserStatisticDTO> data = jdbcTemplate.query(stringBuilder.toString(), new UsersStatisticMapper());
        return data;
    }

    class UsersStatisticMapper implements RowMapper<UserStatisticDTO> {

        @Override
        public UserStatisticDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserStatisticDTO userStatisticDTO = new UserStatisticDTO();
            userStatisticDTO.setNumberPerson(rs.getInt("numberPerson"));
            userStatisticDTO.setEnabled(rs.getInt("enabled"));
            return userStatisticDTO;
        }
    }

    /***
     * Statistic the number of questions for each category
     * @return
     */
    @Override
    public List<QuestionStatisticDTO> questionsStatistic(String fromDate, String toDate) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(" SELECT COUNT(category_id) as totalQuestion , cate.name ");
        strBuilder.append(" FROM question quest ");
        strBuilder.append(" JOIN category cate ON quest.category_id = cate.id ");
        strBuilder.append(" WHERE 1 = 1 ");
        if (fromDate != null && toDate != null){
            strBuilder.append("AND (DATE_FORMAT(quest.created_date, '%Y-%m-%d') BETWEEN ? AND ?) ");
        }
        strBuilder.append("GROUP BY category_id");

        List<QuestionStatisticDTO> listData = jdbcTemplate.query(strBuilder.toString(),
                new Object[] { fromDate, toDate },
                new QuestionStatisticMapper());
        return listData;
    }

    class QuestionStatisticMapper implements RowMapper<QuestionStatisticDTO> {

        @Override
        public QuestionStatisticDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            QuestionStatisticDTO questionStatisticDTO = new QuestionStatisticDTO();
            questionStatisticDTO.setName(resultSet.getString("name"));
            questionStatisticDTO.setTotalQuestions(resultSet.getInt("totalQuestion"));
            return questionStatisticDTO;
        }
    }


    @Override
    public List<TestsByUsersDTO> testByUsers(String fromDate, String toDate) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" SELECT t.name as testName, count(udt.test_id) as totalNumberTest ");
        stringBuilder.append(" FROM user_doing_test udt LEFT JOIN user u ON udt.user_id = u.user_id ");
        stringBuilder.append(" LEFT JOIN test t ON udt.test_id = t.id ");
        stringBuilder.append(" WHERE 1 = 1 ");
        if (fromDate != null && toDate != null) {
            stringBuilder.append(" AND (DATE_FORMAT(udt.created_date, '%Y-%m-%d') BETWEEN ? AND ?) ");
        }

        stringBuilder.append("GROUP BY t.name");

        List<TestsByUsersDTO> listData = jdbcTemplate.query(stringBuilder.toString(),
                new Object[] { fromDate, toDate },
                new testsByUserMapper());
        return listData;
    }


    class testsByUserMapper implements RowMapper<TestsByUsersDTO> {

        @Override
        public TestsByUsersDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            TestsByUsersDTO testsByUsersDTO = new TestsByUsersDTO();
            testsByUsersDTO.setTestName(rs.getString("testName"));
            testsByUsersDTO.setTotalNumberTest(rs.getInt("totalNumberTest"));
            return testsByUsersDTO;
        }
    }




    /**
     * Thống kê số bài test Pass và Fail
     * @param fromDate
     * @param toDate
     * @return
     */
    @Override
    public List<CheckStatusTestDTO> getAllTest(String fromDate, String toDate) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" SELECT count(*) as numberTest , udt.is_pass as status ");
        stringBuilder.append(" FROM user_doing_test udt ");
        stringBuilder.append(" LEFT JOIN test t ON udt.test_id = t.id ");
        stringBuilder.append(" LEFT JOIN user u ON udt.user_id = u.user_id ");

        if (fromDate != null && toDate != null ){
            stringBuilder.append(" WHERE (DATE_FORMAT(udt.created_date, '%Y-%m-%d') BETWEEN ? AND ?) ");
        }
        stringBuilder.append("GROUP BY udt.is_pass");

        List<CheckStatusTestDTO> listData =null;

         listData = jdbcTemplate.query(stringBuilder.toString(),
                new Object[] {fromDate, toDate},
                new AllTestStatusMapper());
        return listData;
    }

    class AllTestStatusMapper implements RowMapper<CheckStatusTestDTO>{
        @Override
        public CheckStatusTestDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            CheckStatusTestDTO checkStatusTestDTO = new CheckStatusTestDTO();
            checkStatusTestDTO.setNumberTest(rs.getInt("numberTest"));
            checkStatusTestDTO.setStatus(rs.getString("status"));
            return checkStatusTestDTO;
        }
    }
}
