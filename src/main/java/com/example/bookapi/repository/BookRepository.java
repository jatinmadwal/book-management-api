package com.example.bookapi.repository;

import com.example.bookapi.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


    @Repository
    public class BookRepository {

        private List<Book> books = new ArrayList<>();

        public void addBook(Book book) {
            books.add(book);
        }

        public List<Book> getAllBooks() {
            return books;
        }

        public Book getBookById(int id) {
            for (Book book : books) {
                if (book.getId() == id) {
                    return book;
                }
            }
            return null;
        }

        public void deleteBook(int id) {
            books.removeIf(book -> book.getId() == id);
        }
        public void updateBook(int id, Book updatedBook) {
            for (Book book : books) {
                if (book.getId() == id) {
                    book.setName(updatedBook.getName());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setPrice(updatedBook.getPrice());
                    return;
                }
            }
        }
    }
