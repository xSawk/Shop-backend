package pl.lukasik.shop.product.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lukasik.shop.product.model.Product;
import pl.lukasik.shop.product.service.ProductService;

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
}
