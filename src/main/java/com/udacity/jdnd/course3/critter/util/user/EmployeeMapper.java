package com.udacity.jdnd.course3.critter.util.user;

import com.udacity.jdnd.course3.critter.dtos.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entities.Employee;

public class EmployeeMapper {
    public static Employee mapDtoToEntity(EmployeeDTO dto) {
        Employee emp = new Employee();
        emp.setName(dto.getName());
        if (dto.getSkills() != null) emp.setSkills(dto.getSkills());
        if (dto.getDaysAvailable() != null) emp.setDaysAvailable(dto.getDaysAvailable());
        return emp;
    }

    public static EmployeeDTO mapEntityToDto(Employee emp) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(emp.getId());
        dto.setName(emp.getName());
        if (emp.getSkills() != null) dto.setSkills(emp.getSkills());
        if (emp.getDaysAvailable() != null) dto.setDaysAvailable(emp.getDaysAvailable());
        return dto;
    }
}
