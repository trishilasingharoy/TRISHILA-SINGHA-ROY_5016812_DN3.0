package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    // Method to handle GET requests to retrieve all books
    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    // Method to handle GET requests to retrieve a specific book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return ResponseEntity.ok(book);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Method to handle POST requests to add a new book
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        books.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    // Method to handle PUT requests to update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                book.setTitle(bookDetails.getTitle());
                book.setAuthor(bookDetails.getAuthor());
                book.setPrice(bookDetails.getPrice());
                book.setIsbn(bookDetails.getIsbn());
                return ResponseEntity.ok(book);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Method to handle DELETE requests to remove a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                books.remove(book);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
