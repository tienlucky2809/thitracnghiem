package fresher.thitracnghiem.service.impl;

import fresher.thitracnghiem.dao.QuestionDAO;
import fresher.thitracnghiem.dao.QuestionRepository;
import fresher.thitracnghiem.dao.QuestionTypeDAO;
import fresher.thitracnghiem.entity.Answer;
import fresher.thitracnghiem.entity.Category;
import fresher.thitracnghiem.entity.Question;
import fresher.thitracnghiem.model.AnswerDTO;
import fresher.thitracnghiem.model.QuestionDTO;
import fresher.thitracnghiem.model.QuestionSearchDTO;
import fresher.thitracnghiem.service.AnswerService;
import fresher.thitracnghiem.service.QuestionService;
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
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDAO questionDAO;
    private final QuestionTypeDAO questionTypeDAO;
    private final ModelMapper modelMapper;
    private final QuestionRepository questionRepository;
    private final AnswerService answerService;

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");

    @Override
    public QuestionDTO add(QuestionDTO questionDTO) {
        questionDTO.setEnabled(true);
        Question question = questionDAO.add(convertToEntity(questionDTO));
        questionDTO.setId(question.getId());
        return questionDTO;
    }

    @Override
    public List<QuestionDTO> addAll(List<QuestionDTO> questionDTOS) {
        List<Question> questions = questionRepository.saveAll(convertToEntityQ(questionDTOS));
        List<QuestionDTO> dtos = convertToDTOQ(questions);
        return dtos;
    }

    @Override
    public QuestionDTO update(QuestionDTO questionDTO) {
        questionDTO.setEnabled(true);
        answerService.delete(questionDTO.getId());
        if (questionDAO.getOne(questionDTO.getId()) != null) {
            return convertToDTO(questionDAO.update(convertToEntity(questionDTO)));
        }
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void disable(long id) {
        questionDAO.disable(id);
    }

    @Override
    public QuestionDTO getOne(long id) {
        return convertToDTO(questionDAO.getOne(id));
    }

    @Override
    public List<QuestionDTO> getAll() {
        QuestionSearchDTO questionSearchDTO = new QuestionSearchDTO();
        questionSearchDTO.setEnabled(true);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        List<Question> questions = questionDAO.search(questionSearchDTO);
        for (Question question : questions
        ) {
            questionDTOS.add(convertToDTO(question));
        }
        return questionDTOS;
    }

    @Override
    public List<QuestionDTO> search(QuestionSearchDTO questionSearchDTO) {
        return convertToDTOQ(questionDAO.search(questionSearchDTO));
    }

    private Question convertToEntity(QuestionDTO questionDTO) {
        Question question = modelMapper.map(questionDTO, Question.class);
        question.setQuestionType(questionTypeDAO.getOneByValue(questionDTO.getQuestionTypeValue()));
        question.setCategory(new Category(questionDTO.getCategoryId()));
        question.setAnswers(new ArrayList<Answer>(convertToEntity(questionDTO.getAnswerDTOS())));
        return question;
    }

    private List<Question> convertToEntityQ(List<QuestionDTO> questionDTOS) {
        List<Question> questions = new ArrayList<>();
        for (QuestionDTO questionDTO : questionDTOS
        ) {
            Question question = modelMapper.map(questionDTO, Question.class);
            question.setEnabled(true);
            question.setQuestionType(questionTypeDAO.getOneByValue(questionDTO.getQuestionTypeValue()));
            question.setCategory(new Category(questionDTO.getCategoryId()));
            question.setAnswers(new ArrayList<Answer>(convertToEntity(questionDTO.getAnswerDTOS())));
            questions.add(question);
        }
        return questions;
    }

    private List<QuestionDTO> convertToDTOQ(List<Question> questionS) {
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questionS
        ) {
            QuestionDTO questionDTO = convertToDTO(question);
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }

    private QuestionDTO convertToDTO(Question question) {
        QuestionDTO questionDTO = modelMapper.map(question, QuestionDTO.class);
        questionDTO.setQuestionTypeValue(question.getQuestionType().getValue());
        questionDTO.setCategoryId(question.getCategory().getId());
        questionDTO.setAnswerDTOS(new ArrayList<>(convertToDTO(question.getAnswers())));

        return questionDTO;
    }

    private List<Answer> convertToEntity(List<AnswerDTO> answerDTOS) {
        List<Answer> answers = new ArrayList<Answer>();
        for (AnswerDTO answerDTO : answerDTOS
        ) {
            Answer answer = modelMapper.map(answerDTO, Answer.class);
            answer.setQuestion(new Question(answerDTO.getQuestionId()));
            answers.add(answer);
        }

        return answers;
    }

    private List<AnswerDTO> convertToDTO(List<Answer> answers) {
        List<AnswerDTO> answerDTOs = new ArrayList<AnswerDTO>();
        for (Answer answer : answers
        ) {
            AnswerDTO answerDTO = modelMapper.map(answer, AnswerDTO.class);
            answerDTO.setQuestionId(answer.getQuestion().getId());
            answerDTOs.add(answerDTO);
        }
        return answerDTOs;
    }
}
