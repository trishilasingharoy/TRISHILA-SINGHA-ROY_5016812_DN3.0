package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.exception.BookNotFoundException;
import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();
    private final ModelMapper modelMapper;

    public BookController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }



    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        books.add(book);
        BookDTO responseBookDTO = modelMapper.map(book, BookDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBookDTO);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book book = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPrice(bookDetails.getPrice());
        book.setIsbn(bookDetails.getIsbn());
        return book;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        Book book = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(id));

        books.remove(book);
    }
}



