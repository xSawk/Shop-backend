package pl.lukasik.shop.product.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lukasik.shop.common.model.Product;
import pl.lukasik.shop.common.model.Review;
import pl.lukasik.shop.common.repository.ProductRepository;
import pl.lukasik.shop.common.repository.ReviewRepository;
import pl.lukasik.shop.product.service.dto.ProductDto;
import pl.lukasik.shop.product.service.dto.ReviewDto;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public ProductService(ProductRepository productRepository, ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    public Page<Product> getProduct(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public ProductDto getProductById(Long id) {

        Product product = productRepository.findById(id).orElseThrow();

        List<Review> reviews = reviewRepository.findAllByProductIdAndModerated(product.getId(), true);

        return mapToProductDto(product, reviews);

    }

    private ProductDto mapToProductDto(Product product, List<Review> reviews) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .categoryId(product.getCategoryId())
                .description(product.getDescription())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .image(product.getImage())
                .reviews(reviews.stream().map(review -> ReviewDto.builder()
                                .id(review.getId())
                                .productId(review.getProductId())
                                .authorName(review.getAuthorName())
                                .content(review.getContent())
                                .moderate(review.isModerated())
                                .build())
                        .toList())
                .build();
    }

}
