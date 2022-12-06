package pl.lukasik.shop.category.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lukasik.shop.category.model.Category;
import pl.lukasik.shop.category.model.CategoryProductsDto;
import pl.lukasik.shop.category.repository.CategoryRepository;
import pl.lukasik.shop.product.model.Product;
import pl.lukasik.shop.product.repo.ProductRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;


    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }


    @Transactional(readOnly = true)
    public CategoryProductsDto getCategoriesWithProduct(Long id, Pageable pageable) {

        Category category = categoryRepository.findById(id).orElseThrow();
        Page<Product> page = productRepository.findByCategoryId(category.getId(), pageable);

        return new CategoryProductsDto(category,page);
    }
}
