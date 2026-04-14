package com.example.bookapi.service;

import com.example.bookapi.model.Book;
import com.example.bookapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public void addBook(Book book) {
        repository.addBook(book);
    }

    public List<Book> getAllBooks() {
        return repository.getAllBooks();
    }

    public Book getBookById(int id) {
        return repository.getBookById(id);
    }

    public void deleteBook(int id) {
        repository.deleteBook(id);
    }
    public void updateBook(int id, Book updatedBook) {
        repository.updateBook(id, updatedBook);
    }
}