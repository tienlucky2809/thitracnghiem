package fresher.thitracnghiem.dao.impl;

import fresher.thitracnghiem.dao.CategoryDAO;
import fresher.thitracnghiem.entity.Category;
import fresher.thitracnghiem.model.CategorySearchDTO;
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
public class CategoryDAOImpl implements CategoryDAO {

    @PersistenceContext
    private EntityManager entityManager;

    //Them category
    @Override
    public Category add(Category category) {
        entityManager.persist(category);
        return category;
    }

    //Cap nhat category
    @Override
    public void update(Category category) {
        entityManager.merge(category);
    }

    //Xoa category
    @Override
    public void delete(long id) {
        entityManager.remove(getOne(id));
    }

    //Disable category
    @Override
    public void disable(long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Category> update = builder.createCriteriaUpdate(Category.class);
        CriteriaUpdate<Category> update1 = builder.createCriteriaUpdate(Category.class);
        Root root = update.from(Category.class);
        Root root1 = update1.from(Category.class);
        update.set("enabled", false);
        update1.set("enabled", false);
        update.where(builder.equal(root.get("id"), id));
        update1.where(builder.equal(root1.get("categoryParent"), id));
        entityManager.createQuery(update).executeUpdate();
        entityManager.createQuery(update1).executeUpdate();
    }

    //Get 1 category
    @Override
    public Category getOne(long id) {
        return entityManager.find(Category.class, id);
    }

    //Get all category
    @Override
    public List<Category> getAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        query.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Category> typedQuery = entityManager.createQuery(query.select(root));
        return typedQuery.getResultList();
    }

    @Override
    public List<Category> search(CategorySearchDTO categorySearchDTO) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (categorySearchDTO.getName() != null) {
            Predicate predicate = builder.like(builder.lower(root.get("name")),
                    "%" + categorySearchDTO.getName().toLowerCase() + "%");
            predicates.add(predicate);
        }

        if (categorySearchDTO.getIsSub() != null) {
            if (categorySearchDTO.getIsSub() == false) {
                Predicate predicate1 = builder.isNull(root.get("categoryParent"));
                predicates.add(predicate1);
            }

            if (categorySearchDTO.getIsSub() == true) {
                Predicate predicate1 = builder.isNotNull(root.get("categoryParent"));
                predicates.add(predicate1);
            }
        }

        if (categorySearchDTO.getCategoryParentId() != null) {
            Predicate predicate1 = builder.equal(root.get("categoryParent"), categorySearchDTO.getCategoryParentId());
            predicates.add(predicate1);
        }

        if (categorySearchDTO.getEnabled() != null) {
            Predicate predicate = builder.equal(root.get("enabled"), categorySearchDTO.getEnabled());
            predicates.add(predicate);
        }

        query.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Category> typedQuery = entityManager.createQuery(query.select(root));
        return typedQuery.getResultList();
    }

}
