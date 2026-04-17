package com.example.bookapi.service;

import com.example.bookapi.exception.BookNotFoundException;
import com.example.bookapi.model.Book;
import com.example.bookapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }


    public Book addBook(Book book) {
        return repository.save(book);
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBookById(int id) {
        Book book = repository.findById(id).orElse(null);

        if (book == null) {
            throw new BookNotFoundException("Book not found with id " + id);
        }
        return book;
    }

    public void deleteBook(int id) {
        repository.deleteById(id);
    }
    public Book updateBook(int id, Book updatedBook) {
        Book existingBook = repository.findById(id).orElse(null);

        if (existingBook != null) {
            existingBook.setName(updatedBook.getName());
            existingBook.setUser(updatedBook.getUser());
            existingBook.setPrice(updatedBook.getPrice());

            return repository.save(existingBook);
        }

        return null;
    }
}