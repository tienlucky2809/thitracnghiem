package fresher.thitracnghiem.dao;

import fresher.thitracnghiem.entity.Category;
import fresher.thitracnghiem.model.CategorySearchDTO;

import java.util.List;

public interface CategoryDAO {
    Category add(Category category);

    void update(Category category);

    void delete(long id);

    void disable(long id);

    Category getOne(long id);

    List<Category> getAll();

    List<Category> search(CategorySearchDTO categorySearchDTO);
}
