package pl.lukasik.shop.category.controller;


import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lukasik.shop.common.model.Category;
import pl.lukasik.shop.category.dto.CategoryProductsDto;
import pl.lukasik.shop.category.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{id}/products")
    public CategoryProductsDto getCategoriesWithProduct(@PathVariable Long id, Pageable pageable) {
        return categoryService.getCategoriesWithProduct(id, pageable);
    }

}
