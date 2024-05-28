package com.example.ems.service;

import com.example.ems.dto.EmployeeDTO;
import com.example.ems.mapper.EmployeeDTOMapper;
import com.example.ems.model.Employee;
import com.example.ems.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeDTOMapper employeeDTOMapper = new EmployeeDTOMapper();

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeDTOMapper.mapEmployeeDTOtoEmployee(employeeDTO);
        return employeeDTOMapper.mapEmployeetoEmployeeDTO(employeeRepository.save(employee));
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeDTOMapper::mapEmployeetoEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeeDTO> getEmployeeById(int id) {

        if (employeeRepository.findById(id).isPresent()) {
            return Optional.of(employeeDTOMapper.mapEmployeetoEmployeeDTO(employeeRepository.findById(id).get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeDTOMapper.mapEmployeeDTOtoEmployee(employeeDTO);
        return employeeDTOMapper.mapEmployeetoEmployeeDTO(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
