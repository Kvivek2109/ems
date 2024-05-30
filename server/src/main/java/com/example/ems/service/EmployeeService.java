package com.example.ems.service;

import com.example.ems.dto.EmployeeDTO;
import java.util.List;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);

    void deleteEmployee(Long id);
}
