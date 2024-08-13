package com.example.employeemanagementsystem9.dto;

public class EmployeeDTO {

    private String name;
    private String email;

    public EmployeeDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and toString method
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
