package com.udacity.jdnd.course3.critter.dao;

import com.udacity.jdnd.course3.critter.dao.iservice.IEmployeeService;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.enums.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.Set;
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
    public Employee getEmployeeById(long id) {
        return _manager.find(Employee.class, id);
    }

    @Override
    public Stream<Employee> findAllAvailableEmployees(DayOfWeek day, Set<EmployeeSkill> employeeSkills) {
        TypedQuery<Employee> query = _manager.createQuery(
                "SELECT DISTINCT e FROM Employee e " +
                        "INNER JOIN e.daysAvailable d " +
                        "WHERE (:dayOfWeek) IN  ELEMENTS(e.daysAvailable)",
                Employee.class);
        query.setParameter("dayOfWeek", day);
        Stream<Employee> emp$ = query.getResultStream();
        if (employeeSkills != null) return emp$.filter(emp -> emp.getSkills().containsAll(employeeSkills));

        return emp$;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void setAvailableDays(long employeeId, Set<DayOfWeek> daysAvailable) {
        Employee emp = _manager.find(Employee.class, employeeId);
        emp.setDaysAvailable(daysAvailable);
        _manager.persist(emp);
    }

    @Override
    public Employee findScheduleByEmpId(long id) {
        TypedQuery<Employee> query = _manager.createQuery(
                "SELECT e FROM Employee e " +
                        "JOIN FETCH e.schedules " +
                        "WHERE e.id = :theId", Employee.class);
        query.setParameter("theId", id);
        Employee emp = query.getSingleResult();
        return emp;
    }
}
