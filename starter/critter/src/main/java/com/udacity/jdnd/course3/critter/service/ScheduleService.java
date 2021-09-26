package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.domain.Schedule;

import java.util.List;

public interface ScheduleService {

    Schedule save(Schedule s);

    List<Schedule> findAll();

    List<Schedule> findSchedulesByPet(Long petId);

    List<Schedule> findSchedulesByEmployee(Long employee);

    List<Schedule> findSchedulesByCustomer(Long customer);
}
