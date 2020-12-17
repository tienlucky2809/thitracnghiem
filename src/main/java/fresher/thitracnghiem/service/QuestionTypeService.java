package fresher.thitracnghiem.service;

import fresher.thitracnghiem.model.QuestionTypeDTO;

import java.util.List;

public interface QuestionTypeService {
    QuestionTypeDTO add(QuestionTypeDTO questionTypeDTO);

    QuestionTypeDTO update(QuestionTypeDTO questionTypeDTO);

    void delete(long id);

    QuestionTypeDTO getOne(long id);

    QuestionTypeDTO getOneByValue(int value);

    List<QuestionTypeDTO> getAll();
}
