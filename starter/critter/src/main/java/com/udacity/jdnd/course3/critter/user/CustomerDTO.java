package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.domain.Customer;
import com.udacity.jdnd.course3.critter.domain.Pet;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
public class CustomerDTO {
    private long id;
    private String name;
    private String phoneNumber;
    private String notes;
    private List<Long> petIds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }

    public void fromCustomer(Customer c)
    {
        this.id = c.getId();
        this.name = c.getName();
        this.phoneNumber = c.getPhoneNumber();
        this.notes = c.getNotes();
        if(c.getPets() != null) {

            this.petIds = c.getPets().stream().map(x -> x.getId()).collect(Collectors.toList());
        }

    }

    public void toCustomer(Customer c)
    {

        c.setId(this.id);
        c.setName(this.name);
        c.setNotes(this.notes);
        c.setPhoneNumber(this.phoneNumber);
        if(this.petIds !=null) {
            List<Pet> pets = this.petIds.stream().map(x -> {
                Pet p = new Pet();
                p.setId(x);
                return p;
            }).collect(Collectors.toList());

            c.setPets(pets);
        }
    }

}
