package com.example.bookstoreapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;

public class BookDTO {
    private Long id;
    private String title;
    private String author;

    @JsonSerialize(using = NumberSerializers.DoubleSerializer.class)
    @JsonProperty("price_in_usd")
    private Double price;
    private String isbn;

    // Constructors, getters, setters, etc.

    public BookDTO() {}

    public BookDTO(Long id, String title, String author, Double price, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
    }

    // Getters and setters
    // ...
}
