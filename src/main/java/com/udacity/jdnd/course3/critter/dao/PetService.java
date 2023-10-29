package com.udacity.jdnd.course3.critter.dao;

import com.udacity.jdnd.course3.critter.dao.iservice.IPetService;
import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Service
public class PetService implements IPetService {
    private final EntityManager _manager;

    @Autowired
    public PetService(EntityManager manager) {
        _manager = manager;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Pet save(Pet pet, long owner_id) {
        Customer customer = _manager.find(Customer.class, owner_id);
        if (customer != null) {
            customer.addPet(pet);
            _manager.persist(customer);
        }
        return pet;
    }

    @Override
    public Customer findPetById(long id) {
        TypedQuery<Customer> query = _manager.createQuery(
                "SELECT c FROM Customer c " +
                        "JOIN FETCH c.pets p " +
                        "WHERE p.id = :theId",
                Customer.class);
        query.setParameter("theId", id);
        return query.getSingleResult();
    }

    @Override
    public Customer findPetsByOwnerId(long id) {
        TypedQuery<Customer> query = _manager.createQuery(
                "SELECT c FROM Customer c " +
                        "JOIN FETCH c.pets " +
                        "WHERE c.id = :theId",
                Customer.class);
        query.setParameter("theId", id);
        return query.getSingleResult();
    }

    @Override
    public Pet findScheduleByPetId(long id) {
        TypedQuery<Pet> query = _manager.createQuery(
                "SELECT p FROM Pet p " +
                        "LEFT JOIN p.schedules " +
                        "WHERE p.id = :theId",
                Pet.class);
        query.setParameter("theId", id);
        return query.getSingleResult();
    }

    @Override
    public Customer findScheduleByOwnerId(long id) {
        TypedQuery<Customer> query = _manager.createQuery(
                "SELECT DISTINCT c FROM Customer c " +
                        "LEFT JOIN c.pets p " +
                        "LEFT JOIN p.schedules " +
                        "WHERE c.id = :theId",
                Customer.class);
        query.setParameter("theId", id);
        return query.getSingleResult();
    }
}
