package pl.lukasik.shop.category.model;

import org.springframework.data.domain.Page;
import pl.lukasik.shop.product.model.Product;

public record CategoryProductsDto(Category category, Page<Product> products) {
}
