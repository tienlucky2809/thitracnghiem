package fresher.thitracnghiem.service;

import fresher.thitracnghiem.entity.Question;
import fresher.thitracnghiem.model.QuestionDTO;
import fresher.thitracnghiem.model.QuestionSearchDTO;

import java.util.List;

public interface QuestionService {
    QuestionDTO add(QuestionDTO questionDTO);

    List<QuestionDTO> addAll(List<QuestionDTO> questionDTOS);

    QuestionDTO update(QuestionDTO questionDTO);

    void delete(long id);

    void disable(long id);

    QuestionDTO getOne(long id);

    List<QuestionDTO> getAll();

    List<QuestionDTO> search(QuestionSearchDTO questionSearchDTO);
}
