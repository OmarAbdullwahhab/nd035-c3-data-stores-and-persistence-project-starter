package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.domain.Customer;
import com.udacity.jdnd.course3.critter.domain.Pet;

import java.time.LocalDate;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */
public class PetDTO {
    private long id;
    private PetType type;
    private String name;
    private long ownerId;
    private LocalDate birthDate;
    private String notes;

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void fromPet(Pet entity)
    {

        this.setId(entity.getId());
        this.setBirthDate(entity.getBirthDate());
        this.setName(entity.getName());
        this.setNotes(entity.getNotes());
        this.setOwnerId(entity.getOwner() != null ? entity.getOwner().getId() : null);
        this.setType(entity.getType());

    }

    public void toPet(Pet entity, Customer c)
    {
        entity.setId(this.getId());
        entity.setBirthDate(this.getBirthDate());
        entity.setName(this.getName());
        entity.setNotes(this.getNotes());
        entity.setOwner(c);
        entity.setType(this.getType());

    }


}
