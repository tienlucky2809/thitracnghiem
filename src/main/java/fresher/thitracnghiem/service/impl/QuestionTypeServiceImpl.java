package fresher.thitracnghiem.service.impl;

import fresher.thitracnghiem.dao.QuestionTypeDAO;
import fresher.thitracnghiem.entity.QuestionType;
import fresher.thitracnghiem.model.QuestionTypeDTO;
import fresher.thitracnghiem.service.QuestionTypeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionTypeServiceImpl implements QuestionTypeService {

    private final QuestionTypeDAO questionTypeDAO;
    private final ModelMapper modelMapper;

    @Override
    public QuestionTypeDTO add(QuestionTypeDTO questionTypeDTO) {
        questionTypeDTO.setEnabled(true);
        questionTypeDAO.add(convertToEntity(questionTypeDTO));
        return questionTypeDTO;
    }

    @Override
    public QuestionTypeDTO update(QuestionTypeDTO questionTypeDTO) {
        questionTypeDAO.update(convertToEntity(questionTypeDTO));
        return null;
    }

    @Override
    public void delete(long id) {
    }

    @Override
    public QuestionTypeDTO getOne(long id) {
        return convertToDTO(questionTypeDAO.getOne(id));
    }

    @Override
    public QuestionTypeDTO getOneByValue(int value) {
        return convertToDTO(questionTypeDAO.getOneByValue(value));
    }

    @Override
    public List<QuestionTypeDTO> getAll() {
        List<QuestionType> questionTypes = questionTypeDAO.getAll();
        List<QuestionTypeDTO> questionTypeDTOS = new ArrayList<>();
        for (QuestionType questionType : questionTypes
        ) {
            questionTypeDTOS.add(convertToDTO(questionType));
        }
        return questionTypeDTOS;
    }

    private QuestionType convertToEntity(QuestionTypeDTO questionTypeDTO) {
        QuestionType questionType = modelMapper.map(questionTypeDTO, QuestionType.class);
        return questionType;
    }

    private QuestionTypeDTO convertToDTO(QuestionType questionType) {
        QuestionTypeDTO questionTypeDTO = modelMapper.map(questionType, QuestionTypeDTO.class);

        return questionTypeDTO;
    }
}
