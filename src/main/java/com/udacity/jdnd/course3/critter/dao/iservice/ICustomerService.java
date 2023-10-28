package com.udacity.jdnd.course3.critter.dao.iservice;

import com.udacity.jdnd.course3.critter.entities.Customer;

import java.util.List;

public interface ICustomerService {
    Customer save(Customer customer);
    Customer findCustomerByPetId(long id);
    List<Customer> getAll();
}
