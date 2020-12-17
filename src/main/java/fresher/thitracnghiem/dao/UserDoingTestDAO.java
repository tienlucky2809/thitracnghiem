package fresher.thitracnghiem.dao;

import fresher.thitracnghiem.entity.User;
import fresher.thitracnghiem.entity.UserDoingTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDoingTestDAO extends JpaRepository<UserDoingTest, Long> {
    @Query("SELECT udt FROM UserDoingTest udt WHERE udt.test.id = ?1 AND udt.user.userId = ?2")
    UserDoingTest getBy(Long idTest, Long idUser);
//    UserDoingTest add(UserDoingTest userDoingTest);
//    void update(UserDoingTest userDoingTest);
//    void delete(long id);
//    UserDoingTest getOne(long id);
//    List<UserDoingTest> getAll();
}
