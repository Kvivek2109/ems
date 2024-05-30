package com.example.ems.service;

import com.example.ems.dto.DepartmentDTO;
import java.util.List;

public interface DepartmentService {

    DepartmentDTO  saveDepartment(DepartmentDTO departmentDTO);

    List<DepartmentDTO> getAllDepartments();

    DepartmentDTO getDepartmentById(Long id);

    DepartmentDTO updateDepartment(DepartmentDTO departmentDTO);

    void deleteDepartment(Long id);
}
