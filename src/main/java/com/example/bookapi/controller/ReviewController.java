package com.example.bookapi.controller;

import com.example.bookapi.model.Review;
import com.example.bookapi.service.ReviewService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping
    public Review addReview(@RequestBody Review review) {
        return service.addReview(review);
    }

    @GetMapping("/{bookId}")
    public List<Review> getReviews(@PathVariable UUID bookId) {
        return service.getReviewsByBook(bookId);
    }
}