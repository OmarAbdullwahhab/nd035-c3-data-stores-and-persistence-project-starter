package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.domain.Employee;
import com.udacity.jdnd.course3.critter.domain.Schedule;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule s = new Schedule();
        scheduleDTO.toSchedule(s);
        s = this.scheduleService.save(s);

        scheduleDTO.fromSchedule(s);
        return scheduleDTO;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = this.scheduleService.findAll();
        List<ScheduleDTO> dtos = schedules.stream().map(x->{
            ScheduleDTO dto = new ScheduleDTO();
            dto.fromSchedule(x);
            return dto;
        }).collect(Collectors.toList());
        return dtos;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = this.scheduleService.findSchedulesByPet(petId);
        List<ScheduleDTO> dtos = schedules.stream().map(x->{
            ScheduleDTO dto  = new ScheduleDTO();
            dto.fromSchedule(x);
            return dto;
        }).collect(Collectors.toList());
        return dtos;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = this.scheduleService.findSchedulesByEmployee(employeeId);
        List<ScheduleDTO> dtos = schedules.stream().map(x->{
            ScheduleDTO dto  = new ScheduleDTO();
            dto.fromSchedule(x);
            return dto;
        }).collect(Collectors.toList());
        return dtos;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = this.scheduleService.findSchedulesByCustomer(customerId);
        List<ScheduleDTO> dtos = schedules.stream().map(x->{
            ScheduleDTO dto  = new ScheduleDTO();
            dto.fromSchedule(x);
            return dto;
        }).collect(Collectors.toList());
        return dtos;
    }
}
