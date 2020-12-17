package fresher.thitracnghiem.dao;

import fresher.thitracnghiem.entity.Question;
import fresher.thitracnghiem.model.QuestionSearchDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionDAO {
    Question add(Question question);

    Question update(Question question);

    void delete(long id);

    void disable(long id);

    Question getOne(long id);

    List<Question> getAll();

    List<Question> search(QuestionSearchDTO questionSearchDTO);
}
