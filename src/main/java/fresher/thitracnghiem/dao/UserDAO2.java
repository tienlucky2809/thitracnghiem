package fresher.thitracnghiem.dao;

import fresher.thitracnghiem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO2 extends JpaRepository<User,Long > {
}
