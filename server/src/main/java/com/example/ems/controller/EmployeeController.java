package com.example.ems.controller;

import com.example.ems.dto.EmployeeDTO;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.model.Employee;
import com.example.ems.service.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDTO), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        if(employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Employee", "Id", id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id,
                                                   @RequestBody Employee employeeDetails) {
        EmployeeDTO currentEmployee = employeeService.getEmployeeById(id);
        if (currentEmployee != null) {
            currentEmployee.setEmail(employeeDetails.getEmail());
            currentEmployee.setFirstName(employeeDetails.getFirstName());
            currentEmployee.setLastName(employeeDetails.getLastName());
            EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployee(currentEmployee);
            return new ResponseEntity<>(updatedEmployeeDTO, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Employee", "Id", id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        if (employee!= null) {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("Employee Deleted Successfully.", HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Employee", "Id", id);
        }
    }
}
