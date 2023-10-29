package com.udacity.jdnd.course3.critter.dao;

import com.udacity.jdnd.course3.critter.dao.iservice.IScheduleService;
import com.udacity.jdnd.course3.critter.dtos.CustomerDTO;
import com.udacity.jdnd.course3.critter.dtos.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.entities.enums.EmployeeSkill;
import org.hibernate.collection.internal.PersistentMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ScheduleService implements IScheduleService {
    private final EntityManager _manager;
    private final EmployeeService _empSvc;
    private final PetService _petSvc;

    @Autowired
    public ScheduleService(EntityManager manager, EmployeeService empSvc, PetService petSvc) {
        _manager = manager;
        _empSvc = empSvc;
        _petSvc = petSvc;
    }

    @Override
    public Schedule save(ScheduleDTO dto) {
        Schedule schedule = new Schedule();

        List<Employee> employees = new ArrayList<>();
        for (long id: dto.getEmployeeIds()) {
            Employee emp = _empSvc.getEmployeeById(id);
            if (emp == null) continue;
            employees.add(emp);
        }

        List<Pet> pets = new ArrayList<>();
        for (long id: dto.getPetIds()) {
            Pet pet = _petSvc.findPetById(id).getPets().stream()
                    .findFirst()
                    .orElse(null);
            if (pet == null) continue;
            pets.add(pet);
        }

        schedule.setDate(dto.getDate());
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        schedule.setActivities(dto.getActivities());

        _manager.persist(schedule);

        for (Pet pet: pets) {
            pet.addSchedule(schedule);
            _manager.persist(pet);
        }
        for (Employee emp: employees) {
            emp.addSchedule(schedule);
            _manager.persist(emp);
        }

        return schedule;
    }

    @Override
    public Stream<Schedule> getAll() {
        TypedQuery<Schedule> query = _manager.createQuery(
                "SELECT s FROM Schedule s " +
                        "LEFT JOIN  s.employees " +
                        "LEFT JOIN s.pets",
                Schedule.class
        );
        return query.getResultStream();
    }

    @Override
    public Stream<Schedule> findScheduleForPet(long petId) {
        return _petSvc.findScheduleByPetId(petId).getSchedules().stream();
    }

    @Override
    public Stream<Schedule> findScheduleForEmployee(long empId) {
        return _empSvc.findScheduleByEmpId(empId).getSchedules().stream();
    }

    @Override
    public Stream<Schedule> findScheduleForCustomer(long customerId) {
        Customer customer = _petSvc.findScheduleByOwnerId(customerId);
        return customer.getPets().stream()
                .flatMap(pet -> pet.getSchedules().stream());
    }
}
