package com.example.bookapi.service;

import com.example.bookapi.dto.PageResponse;
import com.example.bookapi.exception.BookNotFoundException;
import com.example.bookapi.model.Book;
import com.example.bookapi.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.UUID;

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

    public Book getBookById(UUID id) {
        Book book = repository.findById(id).orElse(null);

        if (book == null) {
            throw new BookNotFoundException("Book not found with id " + id);
        }
        return book;
    }

    public void deleteBook(UUID id) {
        repository.deleteById(id);
    }
    public void updateBook(UUID id, Book updatedBook) {
        Book existingBook = repository.findById(id).orElse(null);

        if (existingBook != null) {
            existingBook.setName(updatedBook.getName());
            existingBook.setUser(updatedBook.getUser());
            existingBook.setPrice(updatedBook.getPrice());

            repository.save(existingBook);
        }

    }

    public PageResponse<Book> getAllBooks(Pageable pageable) {

        Page<Book> page = repository.findAll(pageable);
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    public PageResponse<Book> getAllBooks(int size, int page1) {

        Pageable pageable = pageable(size,page1);

        Page<Book> page = repository.findAll(pageable);
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    public Pageable pageable(int size , int page){
        if(size>50){
            size=20;
        }


        return  PageRequest.of(page,size);


    }
}