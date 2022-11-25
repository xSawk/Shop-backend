package pl.lukasik.shop.product.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.lukasik.shop.product.model.Product;
import pl.lukasik.shop.product.repo.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getProduct(Pageable pageable){
        return productRepository.findAll(pageable);
    }
}
