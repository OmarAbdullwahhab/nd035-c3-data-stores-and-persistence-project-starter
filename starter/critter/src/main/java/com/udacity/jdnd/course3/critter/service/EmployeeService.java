package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.domain.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface EmployeeService {

    Employee save(Employee e);
    Employee findById(Long id);
    List<Employee> findAvailableEmployeesForSkills(Set<EmployeeSkill> skills, DayOfWeek date);

}
