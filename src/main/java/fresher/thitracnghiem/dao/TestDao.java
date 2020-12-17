package fresher.thitracnghiem.dao;

import fresher.thitracnghiem.entity.test.Test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface TestDao extends JpaRepository<Test,Long> {
    @Query("SELECT t from Test t where t.category.id=:id")
    List<Test> findTestByCategory(@Param("id") Long id);
}
