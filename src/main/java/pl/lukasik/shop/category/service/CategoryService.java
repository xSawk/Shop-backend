package pl.lukasik.shop.category.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lukasik.shop.common.model.Category;
import pl.lukasik.shop.category.dto.CategoryProductsDto;
import pl.lukasik.shop.category.repository.CategoryRepository;
import pl.lukasik.shop.common.dto.ProductListDto;
import pl.lukasik.shop.common.model.Product;
import pl.lukasik.shop.common.repository.ProductRepository;

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
        List<ProductListDto> productListDtos = page.getContent().stream()
                .map(product -> ProductListDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .currency(product.getCurrency())
                        .image(product.getImage())

                        .build())
                .toList();

        return new CategoryProductsDto(category, new PageImpl<>(productListDtos, pageable ,page.getTotalElements()));
    }
}
