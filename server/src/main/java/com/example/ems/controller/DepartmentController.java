package com.example.ems.controller;

import com.example.ems.dto.DepartmentDTO;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.model.Department;
import com.example.ems.service.DepartmentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) { this.departmentService = departmentService; }

    @PostMapping("/department")
    public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody DepartmentDTO departmentDTO) {
        return new ResponseEntity<>(departmentService.saveDepartment(departmentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        DepartmentDTO department = departmentService.getDepartmentById(id);
        if (department != null) {
            return new ResponseEntity<>(department, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Department", "Id",  id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id,
                                                        @RequestBody Department departmentDetails) {
        DepartmentDTO currentDepartment = departmentService.getDepartmentById(id);
        if (currentDepartment != null) {
            currentDepartment.setDepartmentName(departmentDetails.getDepartmentName());
            currentDepartment.setDepartmentDesc(departmentDetails.getDepartmentDesc());
            DepartmentDTO updatedDepartment = departmentService.updateDepartment(currentDepartment);
            return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Department", "Id", id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        Optional<DepartmentDTO> department = Optional.ofNullable(departmentService.getDepartmentById(id));
        if (department.isPresent()) {
            departmentService.deleteDepartment(id);
            return new ResponseEntity<>("Department Deleted Successfully.", HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Department", "Id", id);
        }
    }
}
