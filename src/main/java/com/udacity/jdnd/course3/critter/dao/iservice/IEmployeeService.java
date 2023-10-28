package com.udacity.jdnd.course3.critter.dao.iservice;

import com.udacity.jdnd.course3.critter.entities.Employee;

import java.util.stream.Stream;

public interface IEmployeeService {
    Employee save(Employee customer);

    Stream<Employee> getAll();

    Employee getEmployeeById(long id);
}
