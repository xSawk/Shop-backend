package pl.lukasik.shop.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukasik.shop.common.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> { }
