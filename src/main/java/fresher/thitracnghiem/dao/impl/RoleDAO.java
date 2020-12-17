package fresher.thitracnghiem.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import fresher.thitracnghiem.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleDAO {

    @Autowired
    private EntityManager entityManager;

    public List<String> getRoleNames(Long userId) {
        String sql = "Select ur.role.roleName from " + UserRole.class.getName() + " ur " //
                + " where ur.user.userId = :userId ";

        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

}