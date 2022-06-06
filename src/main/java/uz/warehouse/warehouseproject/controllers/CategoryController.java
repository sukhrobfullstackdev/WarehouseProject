package uz.warehouse.warehouseproject.controllers;

import org.springframework.web.bind.annotation.*;
import uz.warehouse.warehouseproject.entity.Category;
import uz.warehouse.warehouseproject.payload.CategoryDto;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.findCategoriesService();
    }

    @GetMapping(value = "/{id}")
    public Category getCategory(@PathVariable Integer id) {
        return categoryService.findCategoryService(id);
    }

    @PostMapping
    public Message addCategoryController(@RequestBody CategoryDto categoryDto) {
        return categoryService.addCategoryService(categoryDto); // isActive still stays true!
    }

    @PutMapping(value = "/{id}")
    public Message editCategoryController(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        return categoryService.editCategoryService(id, categoryDto); // isActive still stays true!
    }

    @DeleteMapping(value = "/{id}")
    public Message deleteCategoryController(@PathVariable Integer id) {
        return categoryService.deleteCategoryService(id);
    }
}
