package pl.lukasik.shop.admin.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukasik.shop.admin.product.model.AdminProduct;

public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {
}
