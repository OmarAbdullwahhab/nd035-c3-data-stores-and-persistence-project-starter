package com.udacity.jdnd.course3.critter.domain;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private long id;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="employee_schedules",
    joinColumns = @JoinColumn(name="schedule_id"),
    inverseJoinColumns = @JoinColumn(name="employee_id"))
    private List<Employee> employees;


    @ManyToMany
    @JoinTable(name="pets_schedules",
            joinColumns = @JoinColumn(name="schedule_id"),
            inverseJoinColumns = @JoinColumn(name="employee_id"))
    private List<Pet> pets;

    private LocalDate date;

    @ElementCollection(targetClass = EmployeeSkill.class)
    @JoinTable(name = "sched_skills", joinColumns = @JoinColumn(name = "sched_id"))
    @Column(name = "activities", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
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
}
