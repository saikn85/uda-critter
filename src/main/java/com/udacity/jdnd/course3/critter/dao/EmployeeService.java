package com.udacity.jdnd.course3.critter.dao;

import com.udacity.jdnd.course3.critter.dao.iservice.IEmployeeService;
import com.udacity.jdnd.course3.critter.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.stream.Stream;

@Service
public class EmployeeService implements IEmployeeService {
    private final EntityManager _manager;

    @Autowired
    public EmployeeService(EntityManager manager) {
        _manager = manager;
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public Employee save(Employee employee) {
        _manager.persist(employee);
        return employee;
    }

    @Override
    public Stream<Employee> getAll() {
        return _manager.createQuery("SELECT emp FROM Employee emp").getResultStream();
    }

    @Override
    public Employee getEmployeeById(long id) {
        return _manager.find(Employee.class, id);
    }
}
