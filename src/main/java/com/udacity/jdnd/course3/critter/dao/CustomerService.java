package com.udacity.jdnd.course3.critter.dao;

import com.udacity.jdnd.course3.critter.dao.iservice.ICustomerService;
import com.udacity.jdnd.course3.critter.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.stream.Stream;

@Service
public class CustomerService implements ICustomerService {
    private final EntityManager _manager;

    @Autowired
    public CustomerService(EntityManager manager) {
        _manager = manager;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Customer save(Customer customer) {
        _manager.persist(customer);
        return customer;
    }

    @Override
    public Stream<Customer> getAll() {
        return _manager.createQuery("SELECT customer FROM Customer customer").getResultStream();
    }
}
