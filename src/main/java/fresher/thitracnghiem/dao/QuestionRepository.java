package fresher.thitracnghiem.dao;

import fresher.thitracnghiem.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
