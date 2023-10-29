package com.udacity.jdnd.course3.critter.dao.iservice;

import com.udacity.jdnd.course3.critter.dtos.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entities.Schedule;

import java.util.stream.Stream;

public interface IScheduleService {
    Schedule save(ScheduleDTO dto);

    Stream<Schedule> getAll();

    Stream<Schedule> findScheduleForPet(long petId);

    Stream<Schedule> findScheduleForEmployee(long empId);

    Stream<Schedule> findScheduleForCustomer(long customerId);
}
