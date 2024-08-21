package com.example.bookstoreapi.dto;

public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private String address;

    // Constructors, getters, setters, etc.

    public CustomerDTO() {}

    public CustomerDTO(Long id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    // Getters and setters
    // ...
}
