package fresher.thitracnghiem.service.impl;

import fresher.thitracnghiem.dao.AnswerDAO;
import fresher.thitracnghiem.entity.Answer;
import fresher.thitracnghiem.entity.Question;
import fresher.thitracnghiem.model.AnswerDTO;
import fresher.thitracnghiem.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final ModelMapper modelMapper;
    private final AnswerDAO answerDAO;

    @Override
    public void add(AnswerDTO answerDTO) {
        answerDTO.setEnabled(true);
        answerDAO.save(convertToEntity(answerDTO));
    }

    @Override
    public void addAll(List<AnswerDTO> answerDTOS) {
        answerDAO.saveAll(convertToEntity(answerDTOS));
    }

    @Override
    public AnswerDTO update(AnswerDTO answerDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {
        List<Answer> list = answerDAO.getByQuestionId(id);
        answerDAO.deleteAll(list);
    }

    @Override
    public void disable(Long id) {

    }

    @Override
    public AnswerDTO getOne(Long id) {
        return convertToDTO(answerDAO.getOne(id));
    }

    @Override
    public List<AnswerDTO> getAll() {
        return null;
    }

    private Answer convertToEntity(AnswerDTO answerDTO) {
        Answer answer = modelMapper.map(answerDTO, Answer.class);
        answer.setQuestion(new Question(answerDTO.getQuestionId()));
        return answer;
    }

    private AnswerDTO convertToDTO(Answer answer) {
        AnswerDTO answerDTO = modelMapper.map(answer, AnswerDTO.class);
        answerDTO.setQuestionId(answer.getQuestion().getId());
        return answerDTO;
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
