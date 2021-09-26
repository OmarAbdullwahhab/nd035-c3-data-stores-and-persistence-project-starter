package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.domain.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements  EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee e) {
        return this.employeeRepository.save(e);
    }

    @Override
    public Employee findById(Long id) {
        return this.employeeRepository.findById(id).get();
    }

    @Override
    public List<Employee> findAvailableEmployeesForSkills(Set<EmployeeSkill> skills, DayOfWeek date) {
        List<Employee> availables = this.employeeRepository.findAll()
                .stream().filter(x->x.getDaysAvailable().contains(date))
                .filter(x->x.getSkills().containsAll(skills)).collect(Collectors.toList());

        return availables;
    }
}
