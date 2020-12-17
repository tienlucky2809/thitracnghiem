package fresher.thitracnghiem.service.impl;

import fresher.thitracnghiem.dao.TestDao;
import fresher.thitracnghiem.dao.UserDAO2;
import fresher.thitracnghiem.dao.UserDoingQuestionDAO;
import fresher.thitracnghiem.dao.UserDoingTestDAO;
import fresher.thitracnghiem.dao.impl.UserDAO;
import fresher.thitracnghiem.entity.User;
import fresher.thitracnghiem.entity.UserDoingQuestion;
import fresher.thitracnghiem.entity.UserDoingTest;
import fresher.thitracnghiem.entity.test.Test;
import fresher.thitracnghiem.model.UserDoingQuesionDTO;
import fresher.thitracnghiem.model.UserDoingTestDTO;
import fresher.thitracnghiem.service.UserDoingTestService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDoingTestServiceImpl implements UserDoingTestService {

    private final ModelMapper modelMapper;
    private final UserDAO2 userDAO;
    private final UserDAO userDAO1;
    private final TestDao testDAO;
    private final UserDoingTestDAO userDoingTestDAO;
    private final UserDoingQuestionDAO doingQuestionDAO;

    @Override
    public void add(UserDoingTestDTO userDoingTestDTO) {
        UserDoingTest userDoingTest = modelMapper.map(userDoingTestDTO, UserDoingTest.class);
        User user = userDAO.getOne(userDoingTestDTO.getUser_id());
        Test test = testDAO.getOne(userDoingTestDTO.getTest_id());
        userDoingTest.setUser(user);
        userDoingTest.setTest(test);
        userDoingTestDAO.save(userDoingTest);
    }

    @Override
    public UserDoingTestDTO getOne(Long id) {
        UserDoingTest userDoingTest = userDoingTestDAO.getOne(id);
        UserDoingTestDTO userDoingTestDTO = modelMapper.map(userDoingTest, UserDoingTestDTO.class);
        userDoingTestDTO.setTest_id(userDoingTest.getTest().getId());
        userDoingTestDTO.setUser_id(userDoingTest.getUser().getUserId());
        return userDoingTestDTO;

    }

    @Override
    public List<UserDoingTestDTO> getAll() {
        List<UserDoingTestDTO> listUserDoingTestDTO = new ArrayList<UserDoingTestDTO>();
        List<UserDoingTest> listUserDoingTest = userDoingTestDAO.findAll();
        for (UserDoingTest userDoingTest : listUserDoingTest) {
            UserDoingTestDTO userDoingTestDTO = modelMapper.map(userDoingTest, UserDoingTestDTO.class);
            userDoingTestDTO.setTest_id(userDoingTest.getTest().getId());
            userDoingTestDTO.setUser_id(userDoingTest.getUser().getUserId());
            listUserDoingTestDTO.add(userDoingTestDTO);
        }
        return listUserDoingTestDTO;
    }

    @Override
    public UserDoingTestDTO submit(List<UserDoingQuesionDTO> doingQuesionDTOS) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDoingTestDTO doingTestDTO = new UserDoingTestDTO();
        doingTestDTO.setUser_id(userDAO1.findUserAccount(authentication.getName()).getUserId());
        doingTestDTO.setTest_id(doingQuesionDTOS.get(0).getUserDoingTest_id());
        doingTestDTO.setPoint(doingQuesionDTOS.get(doingQuesionDTOS.size() - 1).getPoint());
        doingTestDTO.setIspass(doingQuesionDTOS.get(doingQuesionDTOS.size() - 1).getIsPass());
        UserDoingTest userDoingTest = userDoingTestDAO.save(convertToEntity(doingTestDTO));
        doingQuesionDTOS.forEach(userDoingQuesionDTO -> userDoingQuesionDTO.setUserDoingTest_id(userDoingTest.getId()));
        doingQuestionDAO.saveAll(convertToEntityQuest(doingQuesionDTOS));
        return convertToDTO(userDoingTest);
    }

    @Override
    public UserDoingTestDTO getBy(Long idTest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDoingTest doingTest = userDoingTestDAO.getBy(idTest, userDAO1.findUserAccount(authentication.getName()).getUserId());

        return convertToDTO(doingTest);
    }

    private UserDoingTest convertToEntity(UserDoingTestDTO userDoingTestDTO) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        UserDoingTest userDoingTest = modelMapper.map(userDoingTestDTO, UserDoingTest.class);
        userDoingTest.setUser(new User(userDoingTestDTO.getUser_id()));
        userDoingTest.setTest(new Test(userDoingTestDTO.getTest_id()));
        return userDoingTest;
    }


    private UserDoingTestDTO convertToDTO(UserDoingTest userDoingTest) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        UserDoingTestDTO userDoingTestDTO = modelMapper.map(userDoingTest, UserDoingTestDTO.class);
        userDoingTestDTO.setTest_id(userDoingTest.getTest().getId());
        userDoingTestDTO.setPoint(userDoingTest.getPoint());
        userDoingTestDTO.setIspass(userDoingTest.getIspass());
        return userDoingTestDTO;
    }

    private List<UserDoingQuestion> convertToEntityQuest(List<UserDoingQuesionDTO> doingQuesionDTOS) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        List<UserDoingQuestion> doingQuestions = new ArrayList<>();
        for (UserDoingQuesionDTO userDoingQuestionDTO : doingQuesionDTOS
        ) {
            for (int i = 0; i < userDoingQuestionDTO.getContent().size(); i++) {
                UserDoingQuestion userDoingQuestion = modelMapper.map(userDoingQuestionDTO, UserDoingQuestion.class);
                userDoingQuestion.setUserDoingTest(new UserDoingTest(userDoingQuestionDTO.getUserDoingTest_id()));
                userDoingQuestion.setContent(userDoingQuestionDTO.getContent().get(i));
                doingQuestions.add(userDoingQuestion);
            }
        }
        return doingQuestions;
    }
}
