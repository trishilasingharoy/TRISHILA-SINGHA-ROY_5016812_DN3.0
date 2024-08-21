package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.BookResource;
import com.example.bookstoreapi.exception.BookNotFoundException;
import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookController(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<BookResource> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::toBookResource)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BookResource> getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        BookResource bookResource = toBookResource(book);
        return ResponseEntity.ok(bookResource);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BookResource> createBook(@Valid @RequestBody BookResource bookResource) {
        Book book = modelMapper.map(bookResource, Book.class);
        bookRepository.save(book);
        BookResource savedBookResource = toBookResource(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBookResource);
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BookResource> updateBook(@PathVariable Long id, @Valid @RequestBody BookResource bookResource) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        modelMapper.map(bookResource, existingBook);
        bookRepository.save(existingBook);
        BookResource updatedBookResource = toBookResource(existingBook);
        return ResponseEntity.ok(updatedBookResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        bookRepository.delete(book);
        return ResponseEntity.noContent().build();
    }

    private BookResource toBookResource(Book book) {
        BookResource bookResource = modelMapper.map(book, BookResource.class);
        Link selfLink = WebMvcLinkBuilder.linkTo(BookController.class).slash(book.getId()).withSelfRel();
        bookResource.add(selfLink);
        // Add other links if needed
        return bookResource;
    }
}

