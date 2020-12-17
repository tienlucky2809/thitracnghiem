package fresher.thitracnghiem.dao.impl;


import fresher.thitracnghiem.dao.UserRegisDAO;
import fresher.thitracnghiem.entity.Category;
import fresher.thitracnghiem.entity.Question;
import fresher.thitracnghiem.entity.User;
import fresher.thitracnghiem.entity.UserRole;
import fresher.thitracnghiem.model.UserDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserRegisDAOImpl implements UserRegisDAO {
    @PersistenceContext
    private EntityManager entityManager;


    //Them user
    @Override
    public User add(User user) {

        entityManager.persist(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        query.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<User> typedQuery = entityManager.createQuery(query.select(root));
        return typedQuery.getResultList();
    }

    @Override
    public List<User> search(UserDTO userDTO) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (userDTO.getUserName() != null) {
            Predicate predicate = builder.like(builder.lower(root.get("userName")),
                    "%" + userDTO.getUserName().toLowerCase() + "%");
            predicates.add(predicate);
        }

//        if (categorySearchDTO.getIsSub() != null) {
//            if (categorySearchDTO.getIsSub() == false) {
//                Predicate predicate1 = builder.isNull(root.get("categoryParent"));
//                predicates.add(predicate1);
//            }
//
//            if (categorySearchDTO.getIsSub() == true) {
//                Predicate predicate1 = builder.isNotNull(root.get("categoryParent"));
//                predicates.add(predicate1);
//            }
//        }

//        if (categorySearchDTO.getCategoryParentId() != null) {
//            Predicate predicate1 = builder.equal(root.get("categoryParent"), categorySearchDTO.getCategoryParentId());
//            predicates.add(predicate1);
//        }

//        if (categorySearchDTO.getEnabled() != null) {
//            Predicate predicate = builder.equal(root.get("enabled"), categorySearchDTO.getEnabled());
//            predicates.add(predicate);
//        }

        query.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<User> typedQuery = entityManager.createQuery(query.select(root));
        return typedQuery.getResultList();

    }

    @Override
    public User getOne(long id) {
        return null;
    }


}

