package com.udacity.jdnd.course3.critter.controllers;

import com.udacity.jdnd.course3.critter.dao.ScheduleService;
import com.udacity.jdnd.course3.critter.dtos.CustomerDTO;
import com.udacity.jdnd.course3.critter.dtos.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.util.schedule.ScheduleMapper;
import com.udacity.jdnd.course3.critter.util.user.CustomerMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService _scheduleSvc;

    public ScheduleController(ScheduleService scheduleSvc) {
        this._scheduleSvc = scheduleSvc;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        try {
            Schedule schedule = _scheduleSvc.save(scheduleDTO);
            return ScheduleMapper.mapEntityToDto(schedule);
        } catch (Exception ex) {
            System.out.println("createSchedule failed : " + ex.getMessage());
            return new ScheduleDTO();
        }
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        try {
            Stream<Schedule> schedules = _scheduleSvc.getAll();
            return schedules.map(schedule -> ScheduleMapper.mapEntityToDto(schedule)).collect(Collectors.toList());
        } catch (Exception ex) {
            System.out.println("getAllSchedules failed : " + ex.getMessage());
            return new ArrayList<>();
        }
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        try {
            Stream<Schedule> schedules = _scheduleSvc.findScheduleForPet(petId);
            return schedules.map(schedule -> ScheduleMapper.mapEntityToDto(schedule)).collect(Collectors.toList());
        } catch (Exception ex) {
            System.out.println("getScheduleForPet failed : " + ex.getMessage());
            return new ArrayList<>();
        }
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        try {
            Stream<Schedule> schedules = _scheduleSvc.findScheduleForEmployee(employeeId);
            return schedules.map(schedule -> ScheduleMapper.mapEntityToDto(schedule)).collect(Collectors.toList());
        } catch (Exception ex) {
            System.out.println("getScheduleForEmployee failed : " + ex.getMessage());
            return new ArrayList<>();
        }
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        try {
            Stream<Schedule> schedules = _scheduleSvc.findScheduleForCustomer(customerId);
            return schedules.map(schedule -> ScheduleMapper.mapEntityToDto(schedule)).collect(Collectors.toList());
        } catch (Exception ex) {
            System.out.println("getScheduleForEmployee failed : " + ex.getMessage());
            return new ArrayList<>();
        }
    }
}
