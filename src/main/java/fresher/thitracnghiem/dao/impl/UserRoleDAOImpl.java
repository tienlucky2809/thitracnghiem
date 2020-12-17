package fresher.thitracnghiem.dao.impl;

import fresher.thitracnghiem.dao.UserRoleDAO;
import fresher.thitracnghiem.entity.UserRole;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserRoleDAOImpl implements UserRoleDAO {
    @PersistenceContext
    private EntityManager entityManager;

    //Them userRole

    @Override
    public UserRole add(UserRole userRole) {
        entityManager.persist(userRole);
        return userRole;
    }

    @Override
    public void update(UserRole userRole) {

    }

    @Override
    public List<UserRole> getAll() {
        return null;
    }


}