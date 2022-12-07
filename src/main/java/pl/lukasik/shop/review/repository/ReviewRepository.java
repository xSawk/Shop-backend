package pl.lukasik.shop.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukasik.shop.common.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
