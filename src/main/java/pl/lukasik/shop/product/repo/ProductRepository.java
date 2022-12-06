package pl.lukasik.shop.product.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lukasik.shop.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {




    Page<Product> findByCategoryId(Long id, Pageable pageable);
}
