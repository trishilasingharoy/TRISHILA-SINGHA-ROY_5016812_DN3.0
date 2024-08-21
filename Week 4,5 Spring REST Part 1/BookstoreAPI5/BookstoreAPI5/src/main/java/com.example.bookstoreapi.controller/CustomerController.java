package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>();

    // POST endpoint to create a new customer using JSON request body
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    // POST endpoint to process form data for customer registration
    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("address") String address,
            @RequestParam("phoneNumber") String phoneNumber) {

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setAddress(address);
        customer.setPhoneNumber(phoneNumber);

        customers.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
}

