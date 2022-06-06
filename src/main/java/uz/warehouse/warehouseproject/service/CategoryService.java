package uz.warehouse.warehouseproject.service;

import org.springframework.stereotype.Service;
import uz.warehouse.warehouseproject.entity.Category;
import uz.warehouse.warehouseproject.payload.CategoryDto;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findCategoriesService() {
        return categoryRepository.findAll();
    }

    public Category findCategoryService(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElseGet(Category::new);
    }

    public Message addCategoryService(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setActive(categoryDto.isActive());
        if (categoryDto.getCategoryParentId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getCategoryParentId());
            if (optionalCategory.isPresent()) {
                Category parentCategory = optionalCategory.get();
                category.setCategoryParent(parentCategory);
            } else {
                return new Message(false, "The category which you want to add a new category has not been found!");
            }
        }
        categoryRepository.save(category);
        return new Message(true, "The new category has been successfully added!");
    }

    public Message editCategoryService(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(categoryDto.getName());
            category.setActive(categoryDto.isActive());
            if (categoryDto.getCategoryParentId() != null || categoryDto.getCategoryParentId() != 0) {
                Optional<Category> optionalCategoryParent = categoryRepository.findById(categoryDto.getCategoryParentId());
                if (optionalCategoryParent.isPresent()) {
                    category.setCategoryParent(optionalCategoryParent.get());
                } else {
                    return new Message(false, "The parent category which you want to assign to this category has not been found!");
                }
            }
            categoryRepository.save(category);
            return new Message(true, "The category has been successfully edited!");
        } else {
            return new Message(false, "The category which you want to edit has not been found in our system!");
        }
    }

    public Message deleteCategoryService(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.delete(optionalCategory.get());
            return new Message(true, "The category has been successfully delete!");
        } else {
            return new Message(false, "The category which you want to delete has not been found!");
        }
    }
}
