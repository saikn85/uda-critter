package com.udacity.jdnd.course3.critter.dao;

import com.udacity.jdnd.course3.critter.dao.iservice.ICustomerService;
import com.udacity.jdnd.course3.critter.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
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
        TypedQuery<Customer> query = _manager.createQuery(
                "SELECT DISTINCT c FROM Customer c " +
                        "LEFT JOIN c.pets",
                Customer.class);
        return query.getResultStream();
    }

    @Override
    public Customer findCustomerByPetId(long id) {
        TypedQuery<Customer> query = _manager.createQuery(
                "SELECT DISTINCT c FROM Customer c " +
                        "JOIN FETCH c.pets p " + "WHERE p.id = :theId",
                Customer.class);
        query.setParameter("theId", id);
        return query.getSingleResult();
    }
}
