package fresher.thitracnghiem.dao;

import fresher.thitracnghiem.entity.QuestionType;

import java.util.List;

public interface QuestionTypeDAO {
    QuestionType add(QuestionType questionType);

    QuestionType update(QuestionType questionType);

    void delete(long id);

    QuestionType getOne(long id);

    QuestionType getOneByValue(int value);

    List<QuestionType> getAll();
}
