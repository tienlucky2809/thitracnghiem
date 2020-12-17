package fresher.thitracnghiem.dao;

import fresher.thitracnghiem.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerDAO extends JpaRepository<Answer, Long> {
    @Query("UPDATE Answer SET enabled = false WHERE id = :id")
    Answer update(@Param("id") Long id);

    @Query("SELECT a FROM Answer a WHERE a.question.id = ?1")
    List<Answer> getByQuestionId(Long id);
}
