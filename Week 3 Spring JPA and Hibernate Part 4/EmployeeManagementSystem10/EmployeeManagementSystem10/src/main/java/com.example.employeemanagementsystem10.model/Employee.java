package com.example.employeemanagementsystem10.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.time.LocalDateTime;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Formula;

@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@Entity
@Table(name = "employees")
@NamedQueries({
        @NamedQuery(
                name = "Employee.findByDepartmentName",
                query = "SELECT e FROM Employee e WHERE e.department.name = :departmentName"
        ),
        @NamedQuery(
                name = "Employee.findByEmailDomain",
                query = "SELECT e FROM Employee e WHERE e.email LIKE :domain"
        )
})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private double salary;
    private double bonus;

    @Formula("salary + bonus")
    private double totalCompensation;

    @NaturalId
    private String email;

    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
