package com.udacity.jdnd.course3.critter.dao.iservice;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;

public interface IPetService {
    Pet save(Pet pet, long owner_id);

    Customer findPetById(long id);

    Customer findPetsByOwnerId(long owner_id);

    Pet findScheduleByPetId(long id);

    Customer findScheduleByOwnerId(long id);
}
