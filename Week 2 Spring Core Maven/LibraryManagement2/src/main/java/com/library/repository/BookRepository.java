package com.library.repository;

import java.util.Arrays;
import java.util.List;

public class BookRepository {
    public List<String> findAll() {
        return Arrays.asList("Book 1", "Book 2", "Book 3");
    }
}
