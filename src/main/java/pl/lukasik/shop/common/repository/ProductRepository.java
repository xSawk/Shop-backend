package pl.lukasik.shop.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lukasik.shop.common.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {




    Page<Product> findByCategoryId(Long id, Pageable pageable);
}
