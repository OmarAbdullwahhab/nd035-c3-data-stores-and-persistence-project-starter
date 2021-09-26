package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.domain.Employee;
import com.udacity.jdnd.course3.critter.domain.Pet;
import com.udacity.jdnd.course3.critter.domain.Schedule;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
public class ScheduleDTO {
    private long id;
    private List<Long> employeeIds;
    private List<Long> petIds;
    private LocalDate date;
    private Set<EmployeeSkill> activities;

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    public void fromSchedule(Schedule schedule)
    {
        this.id = schedule.getId();
        this.activities = schedule.getActivities();
        this.date = schedule.getDate();
        this.employeeIds = schedule.getEmployees().stream().map(x->x.getId()).collect(Collectors.toList());
        this.petIds = schedule.getPets().stream().map(x->x.getId()).collect(Collectors.toList());

    }
    public void toSchedule(Schedule schedule)
    {
        schedule.setId(this.id);
        schedule.setActivities(this.activities);
        schedule.setDate(this.date);
        schedule.setEmployees(this.employeeIds.stream().map(x->{
            Employee e = new Employee();
            e.setId(x);
            return e;
        }).collect(Collectors.toList()));
        schedule.setPets(this.petIds.stream().map(x -> {
            Pet p = new Pet();
            p.setId(x);
            return p;
        }).collect(Collectors.toList()));

    }
}
