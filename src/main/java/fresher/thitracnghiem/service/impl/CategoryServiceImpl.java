package fresher.thitracnghiem.service.impl;

import fresher.thitracnghiem.dao.CategoryDAO;
import fresher.thitracnghiem.entity.Category;
import fresher.thitracnghiem.model.CategoryDTO;
import fresher.thitracnghiem.model.CategorySearchDTO;
import fresher.thitracnghiem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;
    private final ModelMapper modelMapper;

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");

    @Override
    public CategoryDTO add(CategoryDTO categoryDTO) {
        categoryDTO.setEnabled(true);
        Category category = categoryDAO.add(convertToEntity(categoryDTO));
        categoryDTO.setId(category.getId());
        return categoryDTO;
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
        Category category = categoryDAO.getOne(categoryDTO.getId());
        categoryDTO.setEnabled(category.getEnabled());
        if (category != null) {
            categoryDAO.update(convertToEntity(categoryDTO));
        }
        return categoryDTO;
    }

    @Override
    public void delete(long id) {
        categoryDAO.delete(id);
    }

    @Override
    public void disable(long id) {
        categoryDAO.disable(id);
    }

    @Override
    public CategoryDTO getOne(long id) {
        return convertToDTO(categoryDAO.getOne(id));
    }

    @Override
    public List<CategoryDTO> getAll() {
        CategorySearchDTO categorySearchDTO = new CategorySearchDTO();
        categorySearchDTO.setIsSub(false);
        categorySearchDTO.setEnabled(true);
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category : categoryDAO.search(categorySearchDTO)
        ) {
            categoryDTOS.add(convertToDTO(category));
        }
        return categoryDTOS;
    }

    @Override
    public List<CategoryDTO> search(CategorySearchDTO categorySearchDTO) {
        List<CategoryDTO> categorySubDTOS = new ArrayList<>();
        for (Category category : categoryDAO.search(categorySearchDTO)
        ) {
            categorySubDTOS.add(convertToDTO(category));
        }
        return categorySubDTOS;
    }

    private Category convertToEntity(CategoryDTO categoryDTO) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Category category = modelMapper.map(categoryDTO, Category.class);
        if (categoryDTO.getCategoryParentId() != null) {
            category.setCategoryParent(new Category(categoryDTO.getCategoryParentId()));
        }
        return category;
    }


    private CategoryDTO convertToDTO(Category category) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        categoryDTO.setCreatedDate(dateFormat.format(category.getCreatedDate()));
        categoryDTO.setLastModifiedDate(dateFormat.format(category.getLastModifiedDate()));
        if (category.getCategoryParent() != null) {
            categoryDTO.setCategoryParentId(category.getCategoryParent().getId());
            categoryDTO.setCategoryParentName(category.getCategoryParent().getName());
        }
        return categoryDTO;
    }

}
