package com.example.ems.mapper;

import com.example.ems.dto.DepartmentDTO;
import com.example.ems.model.Department;

public class DepartmentDTOMapper {

    public static DepartmentDTO departmenttoDepartmentDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentId(department.getDepartmentId());
        departmentDTO.setDepartmentName(department.getDepartmentName());
        departmentDTO.setDepartmentDesc(department.getDepartmentDesc());
        return departmentDTO;
    }

    public static Department departmentDTOtoDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setDepartmentId(departmentDTO.getDepartmentId());
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setDepartmentDesc(departmentDTO.getDepartmentDesc());
        return department;
    }
}
