package com.example.bookapi.repository;

import com.example.bookapi.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

     List<Book> findByPriceBetween(double min, double max);
    List<Book> findByUser_Name(String name);
    List<Book> findByUser_NameAndPriceBetween(String name, double min, double max);
}
