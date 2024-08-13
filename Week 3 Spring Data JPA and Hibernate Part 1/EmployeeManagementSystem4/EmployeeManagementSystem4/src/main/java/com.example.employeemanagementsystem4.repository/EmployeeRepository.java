package com.example.employeemanagementsystem4.repository;

import com.example.employeemanagementsystem4.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Derived query method to find employees by department
    List<Employee> findByDepartmentId(Long departmentId);

    // Derived query method to find employees by name
    List<Employee> findByNameContaining(String name);
}
