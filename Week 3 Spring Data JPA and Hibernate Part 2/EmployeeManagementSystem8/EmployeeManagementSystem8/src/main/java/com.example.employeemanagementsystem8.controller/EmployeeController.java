package com.example.employeemanagementsystem8.controller;

import com.example.employeemanagementsystem8.dto.EmployeeDTO;
import com.example.employeemanagementsystem8.projection.EmployeeNameEmailProjection;

import com.example.employeemanagementsystem8.model.Employee;
import com.example.employeemanagementsystem8.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;


    // Fetching data using interface-based projection
    @GetMapping("/projection")
    public List<EmployeeNameEmailProjection> getEmployeeNamesAndEmails() {
        return employeeRepository.findAllBy();
    }

    // Fetching data using class-based projection
    @GetMapping("/dto")
    public List<EmployeeDTO> getEmployeeDTOs() {
        return employeeRepository.findAllEmployeeDTOs();
    }

    // Get all employees with pagination and sorting
    @GetMapping
    public Page<Employee> getAllEmployees(
            @RequestParam(defaultValue = "id") String sortBy,
            Pageable pageable) {
        pageable = Pageable.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy));
        return employeeRepository.findAll(pageable);
    }

    // Search employees by department ID with pagination and sorting
    @GetMapping("/search")
    public Page<Employee> searchEmployeesByDepartment(
            @RequestParam Long departmentId,
            @RequestParam(defaultValue = "name") String sortBy,
            Pageable pageable) {
        pageable = Pageable.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy));
        return employeeRepository.findByDepartmentId(departmentId, pageable);
    }


    // Create a new employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get an employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update an employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(employeeDetails.getName());
                    employee.setEmail(employeeDetails.getEmail());
                    employee.setDepartment(employeeDetails.getDepartment());
                    Employee updatedEmployee = employeeRepository.save(employee);
                    return ResponseEntity.ok(updatedEmployee);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employeeRepository.delete(employee);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
