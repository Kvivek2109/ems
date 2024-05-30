package com.example.ems.service;

import com.example.ems.dto.DepartmentDTO;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.mapper.DepartmentDTOMapper;
import com.example.ems.model.Department;
import com.example.ems.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        Department department = DepartmentDTOMapper.departmentDTOtoDepartment(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentDTOMapper.departmenttoDepartmentDTO(savedDepartment);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return  departments.stream().map((department -> DepartmentDTOMapper.departmenttoDepartmentDTO(department))).collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        if(departmentRepository.findById(id).isPresent()) {
            Department department = departmentRepository.findById(id).get();
            return DepartmentDTOMapper.departmenttoDepartmentDTO(department);
        } else {
            throw new ResourceNotFoundException("Department", "id", id);
        }
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) {
        Department department = DepartmentDTOMapper.departmentDTOtoDepartment(departmentDTO);
        return DepartmentDTOMapper.departmenttoDepartmentDTO(departmentRepository.save(department));
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
