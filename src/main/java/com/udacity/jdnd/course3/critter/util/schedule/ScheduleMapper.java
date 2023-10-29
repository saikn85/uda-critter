package com.udacity.jdnd.course3.critter.util.schedule;

import com.udacity.jdnd.course3.critter.dtos.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entities.Schedule;

import java.util.stream.Collectors;

public class ScheduleMapper {
    public static ScheduleDTO mapEntityToDto(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setDate(schedule.getDate());
        dto.setEmployeeIds(schedule.getEmployees().stream().map(emp -> emp.getId()).collect(Collectors.toList()));
        dto.setPetIds(schedule.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
        dto.setActivities(schedule.getActivities());
        return dto;
    }
}
