package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.domain.Customer;
import com.udacity.jdnd.course3.critter.domain.Employee;
import com.udacity.jdnd.course3.critter.domain.Pet;
import com.udacity.jdnd.course3.critter.domain.Schedule;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleServiceImpl implements  ScheduleService{

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    public Schedule save(Schedule s) {
        return this.scheduleRepository.save(s);
    }

    @Override
    public List<Schedule> findAll() {
        return this.scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> findSchedulesByPet(Long petId) {
        Pet p = this.petRepository.findById(petId).get();

        List<Schedule> found =
                this.scheduleRepository.findAll().stream().filter(x->x.getPets().contains(p)).collect(Collectors.toList());
        return found;
    }

    @Override
    public List<Schedule> findSchedulesByEmployee(Long employee) {
        Employee e = this.employeeRepository.findById(employee).get();

        List<Schedule> found =
                this.scheduleRepository.findAll().stream()
                        .filter(x->x.getEmployees().contains(e))
                        .collect(Collectors.toList());
        return found;
    }

    @Override
    public List<Schedule> findSchedulesByCustomer(Long customer) {
        List<Schedule> schedules = new ArrayList<>();
        List<Pet> pets = this.petRepository.findAllByOwnerId(customer);
        for (Pet pet: pets
             ) {
            schedules.addAll(this.findSchedulesByPet(pet.getId()));

        }
        return schedules;
    }
}
