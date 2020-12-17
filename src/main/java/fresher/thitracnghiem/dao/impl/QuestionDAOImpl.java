package fresher.thitracnghiem.dao.impl;

import fresher.thitracnghiem.dao.QuestionDAO;
import fresher.thitracnghiem.entity.Question;
import fresher.thitracnghiem.model.QuestionSearchDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class QuestionDAOImpl implements QuestionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    //Them question
    @Override
    public Question add(Question question) {
        entityManager.persist(question);
        return question;
    }

    //Cap nhat question
    @Override
    public Question update(Question question) {
        entityManager.merge(question);
        return question;
    }

    //Delete question
    @Override
    public void delete(long id) {
        entityManager.remove(getOne(id));
    }

    //Disable question
    @Override
    public void disable(long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Question> update = builder.
                createCriteriaUpdate(Question.class);
        Root root = update.from(Question.class);
        update.set("enabled", false);
        update.where(builder.equal(root.get("id"), id));
        entityManager.createQuery(update).executeUpdate();
    }

    //Get 1 question
    @Override
    public Question getOne(long id) {
        return entityManager.find(Question.class, id);
    }

    //Get all question
    @Override
    public List<Question> getAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> query = builder.createQuery(Question.class);
        Root<Question> root = query.from(Question.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        query.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Question> typedQuery = entityManager.createQuery(query.select(root));
        return typedQuery.getResultList();
    }

    @Override
    public List<Question> search(QuestionSearchDTO questionSearchDTO) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> query = builder.createQuery(Question.class);
        Root<Question> root = query.from(Question.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (questionSearchDTO.getContent() != null) {
            Predicate predicate = builder.like(builder.lower(root.get("content")),
                    "%" + questionSearchDTO.getContent().toLowerCase() + "%");
            predicates.add(predicate);
        }

        if (questionSearchDTO.getLevel() > 0) {
            Predicate predicate1 = builder.equal(root.get("level"), questionSearchDTO.getLevel());
            predicates.add(predicate1);
        }

        if (questionSearchDTO.getEnabled() != null) {
            Predicate predicate = builder.equal(root.get("enabled"), questionSearchDTO.getEnabled());
            predicates.add(predicate);
        }

        if (questionSearchDTO.getCategoryId() != null) {
            Predicate predicate = builder.equal(root.get("category"), questionSearchDTO.getCategoryId());
            predicates.add(predicate);
        }

        query.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Question> typedQuery = entityManager.createQuery(query.select(root));
        return typedQuery.getResultList();
    }
}
