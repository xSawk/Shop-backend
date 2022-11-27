package pl.lukasik.shop.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukasik.shop.admin.model.AdminProduct;

public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {
}
