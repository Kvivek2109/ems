package com.example.ems.service;

import com.example.ems.dto.EmployeeDTO;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.mapper.EmployeeDTOMapper;
import com.example.ems.model.Employee;
import com.example.ems.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeDTOMapper.mapEmployeeDTOtoEmployee(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeDTOMapper.mapEmployeetoEmployeeDTO(savedEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeDTOMapper::mapEmployeetoEmployeeDTO).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        if (employeeRepository.findById(id).isPresent()) {
            Employee employee = employeeRepository.findById(id).get();
            return EmployeeDTOMapper.mapEmployeetoEmployeeDTO(employee);
        } else {
            throw new ResourceNotFoundException("Employee", "id", id);
        }
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeDTOMapper.mapEmployeeDTOtoEmployee(employeeDTO);
        return EmployeeDTOMapper.mapEmployeetoEmployeeDTO(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
