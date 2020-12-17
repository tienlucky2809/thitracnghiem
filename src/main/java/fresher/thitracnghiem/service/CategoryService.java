package fresher.thitracnghiem.service;

import fresher.thitracnghiem.model.CategoryDTO;
import fresher.thitracnghiem.model.CategorySearchDTO;
import fresher.thitracnghiem.model.SearchDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO add(CategoryDTO categoryDTO);

    CategoryDTO update(CategoryDTO categoryDTO);

    void delete(long id);

    void disable(long id);

    CategoryDTO getOne(long id);

    List<CategoryDTO> getAll();

    List<CategoryDTO> search(CategorySearchDTO categorySearchDTO);


}
