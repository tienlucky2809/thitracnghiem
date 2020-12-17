package fresher.thitracnghiem.service;

import fresher.thitracnghiem.model.AnswerDTO;

import java.util.List;

public interface AnswerService {
    void add(AnswerDTO answerDTO);

    void addAll(List<AnswerDTO> answerDTOS);

    AnswerDTO update(AnswerDTO answerDTO);

    void delete(Long id);

    void disable(Long id);

    AnswerDTO getOne(Long id);

    List<AnswerDTO> getAll();
}
