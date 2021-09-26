package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.domain.Customer;
import com.udacity.jdnd.course3.critter.domain.Pet;

import java.util.List;

public interface PetService {

    Customer getOwnerByPetId(Long id);

    Pet save(Pet p);

    Pet findById(Long id);

    List<Pet> findAll();

    List<Pet> findAllByOwnerId(Long ownerId);
}
