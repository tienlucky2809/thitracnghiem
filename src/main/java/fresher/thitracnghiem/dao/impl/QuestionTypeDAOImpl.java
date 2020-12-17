package fresher.thitracnghiem.dao.impl;

import fresher.thitracnghiem.dao.QuestionTypeDAO;
import fresher.thitracnghiem.entity.QuestionType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class QuestionTypeDAOImpl implements QuestionTypeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public QuestionType add(QuestionType questionType) {
        entityManager.persist(questionType);
        return questionType;
    }

    @Override
    public QuestionType update(QuestionType questionType) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public QuestionType getOne(long id) {
        return entityManager.find(QuestionType.class, id);
    }

    @Override
    public QuestionType getOneByValue(int value) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionType> query = builder.
                createQuery(QuestionType.class);
        Root root = query.from(QuestionType.class);
        query.where(builder.equal(root.get("value"), value));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public List<QuestionType> getAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionType> query = builder.createQuery(QuestionType.class);
        Root<QuestionType> root = query.from(QuestionType.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        query.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<QuestionType> typedQuery = entityManager.createQuery(query.select(root));
        return typedQuery.getResultList();
    }
}
