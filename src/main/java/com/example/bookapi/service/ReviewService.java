package com.example.bookapi.service;
import com.example.bookapi.model.Review;
import com.example.bookapi.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public Review addReview(Review review) {
        return repository.save(review);
    }

    public List<Review> getReviewsByBook(UUID bookId) {
        return repository.findByBookId(bookId);
    }
}