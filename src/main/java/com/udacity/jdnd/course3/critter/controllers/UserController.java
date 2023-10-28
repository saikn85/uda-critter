package com.udacity.jdnd.course3.critter.controllers;

import com.udacity.jdnd.course3.critter.dao.CustomerService;
import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.dtos.CustomerDTO;
import com.udacity.jdnd.course3.critter.dtos.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dtos.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.util.user.CustomerMapper;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private CustomerService _custSvc;

    public UserController(CustomerService custSvc) {
        _custSvc = custSvc;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            Customer customer = _custSvc.save(CustomerMapper.mapDtoToEntity(customerDTO));
            return CustomerMapper.mapEntityToDto(customer);
        } catch (Exception ex) {
            System.out.println("save customer failed : " + ex.getMessage());
            return new CustomerDTO();
        }
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        try {
            return  _custSvc.getAll()
                    .map(customer -> CustomerMapper.mapEntityToDto(customer))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            System.out.println("get all customer failed : " + ex.getMessage());
            return new ArrayList<>();
        }
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

}
