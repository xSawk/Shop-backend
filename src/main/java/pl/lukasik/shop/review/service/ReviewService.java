package pl.lukasik.shop.review.service;


import org.springframework.stereotype.Service;
import pl.lukasik.shop.review.model.Review;
import pl.lukasik.shop.review.repository.ReviewRepository;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;


    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Review review){
        return reviewRepository.save(review);
    }
}
