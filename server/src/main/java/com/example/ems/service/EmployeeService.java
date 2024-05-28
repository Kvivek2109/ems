package com.example.ems.service;

import com.example.ems.dto.EmployeeDTO;
import com.example.ems.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployees();

    Optional<EmployeeDTO> getEmployeeById(int id);

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);

    void deleteEmployee(int id);
}
