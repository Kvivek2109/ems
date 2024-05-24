package com.example.ems.service;

import com.example.ems.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(int id);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(int id);
}
