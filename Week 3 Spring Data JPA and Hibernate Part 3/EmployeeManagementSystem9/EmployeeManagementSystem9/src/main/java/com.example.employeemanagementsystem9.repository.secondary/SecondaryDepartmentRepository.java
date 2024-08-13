package com.example.employeemanagementsystem.repository.secondary;

import com.example.employeemanagementsystem.model.secondary.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondaryDepartmentRepository extends JpaRepository<Department, Long> {

}
