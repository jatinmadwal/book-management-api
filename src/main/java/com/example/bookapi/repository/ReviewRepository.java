package com.example.bookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bookapi.model.Review;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByBookId(UUID bookId);
}