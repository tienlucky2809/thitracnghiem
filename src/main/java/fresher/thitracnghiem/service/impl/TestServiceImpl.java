package fresher.thitracnghiem.service.impl;

import fresher.thitracnghiem.dao.CategoryDAO;
import fresher.thitracnghiem.dao.QuestionDAO;
import fresher.thitracnghiem.dao.StatisticDAO;
import fresher.thitracnghiem.dao.TestDao;
import fresher.thitracnghiem.entity.Category;
import fresher.thitracnghiem.entity.Question;
import fresher.thitracnghiem.entity.test.Test;
import fresher.thitracnghiem.model.*;
import fresher.thitracnghiem.service.QuestionService;
import fresher.thitracnghiem.service.TestService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");

    private final ModelMapper modelMapper;
    private final TestDao testDao;
    private final QuestionDAO questionDAO;
    private final CategoryDAO categoryDAO;
    private final StatisticDAO statisticDAO;
    private final QuestionService questionService;

    @Override
    public TestDTO add(TestDTO testDTO) {

        Test test = modelMapper.map(testDTO, Test.class);
        Category category = categoryDAO.getOne(testDTO.getCategory_id());
        test.setCategory(category);
        for (Long id : testDTO.getListQuestionId()
        ) {
            Question question = questionDAO.getOne(id);
            test.getListQuestion().add(question);
        }
        testDao.save(test);
        return testDTO;
    }

    @Override
    public TestDTO getOne(Long id) {
        Test test = testDao.getOne(id);
        TestDTO testDTO = modelMapper.map(test, TestDTO.class);
        testDTO.setCategory_id(test.getCategory().getId());
        List<Question> questionList = test.getListQuestion();
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        Long[] idQuest = new Long[questionList.size()];
        for (int i = 0; i < questionList.size(); i++) {
            idQuest[i] = questionList.get(i).getId();
            questionDTOS.add(questionService.getOne(questionList.get(i).getId()));
        }
        testDTO.setQuestionDTOS(questionDTOS);
        testDTO.setListQuestionId(idQuest);
        return testDTO;
    }

    @Override
    public List<TestDTO> getAll() {
        List<TestDTO> listTestDTO = new ArrayList<>();
        List<Test> listTest = testDao.findAll();
        for (Test test : listTest
        ) {
            List<Question> questionList = test.getListQuestion();
            Long[] idQuest = new Long[questionList.size()];
            for (int i = 0; i < questionList.size(); i++) {
                idQuest[i] = questionList.get(i).getId();
            }

            TestDTO testDTO = converToDTO(test);
            testDTO.setListQuestionId(idQuest);
            listTestDTO.add(testDTO);
        }

        return listTestDTO;
    }

    @Override
    public List<TestDTO> getTestByCategory(Long id) {
        List<Test> listTest = testDao.findTestByCategory(id);
        List<TestDTO> listTestDTO = new ArrayList<TestDTO>();
        for (Test test : listTest) {
            TestDTO testDTO = modelMapper.map(test, TestDTO.class);
            List<Question> questionList = test.getListQuestion();
            Long[] idQuest = new Long[questionList.size()];
            for (int i = 0; i < questionList.size(); i++) {
                idQuest[i] = questionList.get(i).getId();
            }
            testDTO.setListQuestionId(idQuest);
            listTestDTO.add(testDTO);
        }
        return listTestDTO;

    }


    @Override
    public TestDTO update(TestDTO testDTO) {
        this.add(testDTO);
        return testDTO;
    }


    @Override
    public Boolean delete(Long id) {
        Test test = testDao.getOne(id);
        if (test == null) {
            System.out.println("\nBai thi khong ton tai");
            return false;
        } else {
            test.setEnabled(false);
            testDao.save(test);
            return true;
        }
    }

    public TestDTO converToDTO(Test test) {
        TestDTO testDTO = modelMapper.map(test, TestDTO.class);
        testDTO.setCreateDate(dateFormat.format(test.getCreatedDate()));
        return testDTO;
    }

    public Test converToEntity(TestDTO testDTO) {
        Test test = modelMapper.map(testDTO, Test.class);
        return test;
    }

    //map list question entity -> list question DTO
    private List<QuestionDTO> convertListToListDTO(List<Question> questionList) {
        List<QuestionDTO> listQuestionDTO = new ArrayList<>();
        for (Question q : questionList
        ) {
            QuestionDTO questionDTO = modelMapper.map(q, QuestionDTO.class);
            listQuestionDTO.add(questionDTO);
        }
        return listQuestionDTO;
    }


    //map test DTO -> test Entity
    private Test convertToEntity(TestDTO testDTO) {
        Test test = modelMapper.map(testDTO, Test.class);
        // test.setListQuestion(convertListToListEntity(testDTO.getListQuestionDTO()));
        return test;
    }


    //map question DTO -> question Entity
    private List<Question> convertListToListEntity(List<QuestionDTO> list) {
        List<Question> questionList = new ArrayList<>();
        for (QuestionDTO questionDTO : list
        ) {
            Question question2 = modelMapper.map(questionDTO, Question.class);
            questionList.add(question2);
        }
        return questionList;
    }


    @Override
    public List<TestStatisticDTO> statisticByTest() {
        return statisticDAO.testsStatistic();
    }

    @Override
    public List<UserStatisticDTO> statisticByUsers() {
        return statisticDAO.usersStatistic();
    }

    @Override
    public List<QuestionStatisticDTO> statisticByQuestions(String fromDate, String toDate) {
        return statisticDAO.questionsStatistic(fromDate, toDate);
    }

    @Override
    public List<TestsByUsersDTO> statisticTestsByUsers(String fromDate, String toDate) {
        return statisticDAO.testByUsers(fromDate, toDate);
    }

    @Override
    public List<CheckStatusTestDTO> getAllTestPassAndFailed(String fromDate, String toDate) {
        return statisticDAO.getAllTest(fromDate, toDate);
    }

}
