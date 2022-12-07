package pl.lukasik.shop.review.controller;


import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lukasik.shop.review.controller.dto.ReviewDto;
import pl.lukasik.shop.review.model.Review;
import pl.lukasik.shop.review.service.ReviewService;

import javax.validation.Valid;

@RestController
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/reviews")
    public Review addReview(@RequestBody @Valid ReviewDto reviewDto) {
        return reviewService.addReview(Review.builder()
                .authorName(cleanText(reviewDto.authorName()))
                .content(cleanText(reviewDto.content()))
                .productId(reviewDto.productId())
                .build());
    }

    private String cleanText(String text) {
        return Jsoup.clean(text, Safelist.none());
    }

}
