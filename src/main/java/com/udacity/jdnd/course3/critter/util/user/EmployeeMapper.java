package com.udacity.jdnd.course3.critter.util.user;

import com.udacity.jdnd.course3.critter.dtos.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entities.Employee;

public class EmployeeMapper {
    public static Employee mapDtoToEntity(EmployeeDTO dto){
        return new Employee(dto.getName(), dto.getSkills());
    }

    public static EmployeeDTO mapEntityToDto(Employee emp){
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(emp.getId());
        dto.setName(emp.getName());
        dto.setSkills(emp.getSkills());
        return dto;
    }
}
