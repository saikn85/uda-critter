package com.udacity.jdnd.course3.critter.dao.iservice;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.enums.EmployeeSkill;

import java.time.DayOfWeek;
import java.util.Set;
import java.util.stream.Stream;

public interface IEmployeeService {
    Employee save(Employee customer);

    void setAvailableDays(long employeeId, Set<DayOfWeek> daysAvailable);

    Employee getEmployeeById(long id);

    Stream<Employee> findAllAvailableEmpWithSkills(DayOfWeek day, Set<EmployeeSkill> employeeSkills);
}
