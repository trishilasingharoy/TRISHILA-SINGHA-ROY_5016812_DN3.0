package com.example.employeemanagementsystem10.service;

import com.example.employeemanagementsystem10.model.Employee;
import com.example.employeemanagementsystem10.repository.primary.PrimaryEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void saveAllEmployees(List<Employee> employees) {
        int batchSize = 20; // Define your batch size
        for (int i = 0; i < employees.size(); i++) {
            entityManager.persist(employees.get(i));
            if (i > 0 && i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }
}



}
