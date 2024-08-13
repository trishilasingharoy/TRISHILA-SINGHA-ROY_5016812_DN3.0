package com.example.employeemanagementsystem4.repository;

import com.example.employeemanagementsystem4.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Derived query method to find a department by name
    Department findByName(String name);
}
