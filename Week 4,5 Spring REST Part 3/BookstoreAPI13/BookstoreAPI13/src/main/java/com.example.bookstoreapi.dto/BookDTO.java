package com.example.bookstoreapi.dto;

import org.springframework.hateoas.RepresentationModel;

public class BookResource extends RepresentationModel<BookResource> {
    private Long id;
    private String title;
    private String author;
    private Double price;
    private String isbn;

    // Constructors, getters, setters
    // ...
}

