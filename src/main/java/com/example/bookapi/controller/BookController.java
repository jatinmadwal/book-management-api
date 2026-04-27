package com.example.bookapi.controller;

import com.example.bookapi.dto.PageResponse;
import com.example.bookapi.model.Book;
import com.example.bookapi.service.BookService;
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

    //better readabilty for passing parameters
    @GetMapping("/p")
    public PageResponse<Book> getAllBooks1( @RequestParam(value = "page",defaultValue = "0") int page,@RequestParam(value = "size", defaultValue = "5") int size) {
        return service.getAllBooks(page,size);
    }
    @GetMapping("/price")
    public List<Book> getByPriceRange(
            @RequestParam double min,
            @RequestParam double max) {

        return service.getBooksByPriceRange(min, max);
    }
    @GetMapping("/author")
    public List<Book> getByAuthor(@RequestParam String name) {
        return service.getBooksByAuthor(name);
    }
    @GetMapping("/filter")
    public List<Book> filterBooks(
            @RequestParam String name,
            @RequestParam double min,
            @RequestParam double max) {

        return service.filterBooks(name, min, max);
    }

}