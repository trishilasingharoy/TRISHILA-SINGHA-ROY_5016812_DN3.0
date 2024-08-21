package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.CustomerResource;
import com.example.bookstoreapi.exception.CustomerNotFoundException;
import com.example.bookstoreapi.model.Customer;
import com.example.bookstoreapi.repository.CustomerRepository;
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
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerController(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<CustomerResource> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::toCustomerResource)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CustomerResource> getCustomerById(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        CustomerResource customerResource = toCustomerResource(customer);
        return ResponseEntity.ok(customerResource);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CustomerResource> createCustomer(@Valid @RequestBody CustomerResource customerResource) {
        Customer customer = modelMapper.map(customerResource, Customer.class);
        customerRepository.save(customer);
        CustomerResource savedCustomerResource = toCustomerResource(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomerResource);
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CustomerResource> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerResource customerResource) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        modelMapper.map(customerResource, existingCustomer);
        customerRepository.save(existingCustomer);
        CustomerResource updatedCustomerResource = toCustomerResource(existingCustomer);
        return ResponseEntity.ok(updatedCustomerResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        customerRepository.delete(customer);
        return ResponseEntity.noContent().build();
    }

    private CustomerResource toCustomerResource(Customer customer) {
        CustomerResource customerResource = modelMapper.map(customer, CustomerResource.class);
        Link selfLink = WebMvcLinkBuilder.linkTo(CustomerController.class).slash(customer.getId()).withSelfRel();
        customerResource.add(selfLink);
        // Add other links if needed
        return customerResource;
    }
}
