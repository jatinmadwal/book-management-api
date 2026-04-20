package com.example.bookapi.controller;

import com.example.bookapi.dto.PageResponse;
import com.example.bookapi.model.Book;
import com.example.bookapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }
    @PostMapping
    public Book addBook(@Valid @RequestBody Book book) {
        return service.addBook(book);
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

//    @GetMapping("/{id}")
//    public Book getBookById(@PathVariable int id) {
//        return service.getBookById(id);
//    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable UUID id) {
        service.deleteBook(id);
        return "Book deleted successfully";
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable UUID id, @RequestBody Book updatedBook) {
        service.updateBook(id, updatedBook);
        return "Book updated successfully";
    }
    @GetMapping
    public PageResponse<Book> getAllBooks(Pageable pageable) {
        return service.getAllBooks(pageable);
    }

    @GetMapping("/p")
    public PageResponse<Book> getAllBooks1(@RequestParam(value = "size", defaultValue = "5") int size , @RequestParam(value = "page",defaultValue = "0") int page) {
        return service.getAllBooks(size,page);
    }


}