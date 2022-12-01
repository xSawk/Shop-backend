package pl.lukasik.shop.product.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.lukasik.shop.product.model.Product;
import pl.lukasik.shop.product.service.ProductService;

import java.util.Optional;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public Page<Product> getProducts(Pageable pageable){
        return productService.getProduct(pageable);
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id){
        return productService.getProductById(id);

    }
}
