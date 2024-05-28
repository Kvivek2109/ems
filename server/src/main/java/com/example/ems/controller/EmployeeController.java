package com.example.ems.controller;

import com.example.ems.dto.EmployeeDTO;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.model.Employee;
import com.example.ems.service.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(employeeService.createEmployee(employeeDTO), HttpStatus.CREATED);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int id) {
        Optional<EmployeeDTO> employee = employeeService.getEmployeeById(id);
        if(employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Employee", "Id", id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable int id,
                                                   @RequestBody Employee employeeDetails) {
        Optional<EmployeeDTO> currentEmployee = employeeService.getEmployeeById(id);
        if (currentEmployee.isPresent()) {
            EmployeeDTO employee = currentEmployee.get();
            employee.setEmail(employeeDetails.getEmail());
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployee(employee);
            return new ResponseEntity<>(updatedEmployeeDTO, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Employee", "Id", id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        Optional<EmployeeDTO> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("Employee Deleted Successfully.", HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Employee", "Id", id);
        }
    }
}
