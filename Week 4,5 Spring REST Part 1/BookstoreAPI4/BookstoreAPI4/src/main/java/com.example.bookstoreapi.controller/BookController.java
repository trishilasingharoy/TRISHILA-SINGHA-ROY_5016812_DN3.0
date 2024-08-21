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

    // Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    // Get a book by ID using path variable
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return ResponseEntity.ok(book);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Create a new book
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        books.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    // Update a book by ID using path variable
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

    // Delete a book by ID using path variable
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

    // Search books by title and/or author using query parameters
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {

        List<Book> filteredBooks = new ArrayList<>();

        for (Book book : books) {
            boolean matchesTitle = (title == null || book.getTitle().equalsIgnoreCase(title));
            boolean matchesAuthor = (author == null || book.getAuthor().equalsIgnoreCase(author));

            if (matchesTitle && matchesAuthor) {
                filteredBooks.add(book);
            }
        }

        return ResponseEntity.ok(filteredBooks);
    }
}
