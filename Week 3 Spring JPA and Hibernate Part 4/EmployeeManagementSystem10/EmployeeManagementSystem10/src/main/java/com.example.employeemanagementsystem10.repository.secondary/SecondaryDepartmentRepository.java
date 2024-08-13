package com.example.employeemanagementsystem10.repository.secondary;

import com.example.employeemanagementsystem10.model.secondary.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondaryDepartmentRepository extends JpaRepository<Department, Long> {

}
