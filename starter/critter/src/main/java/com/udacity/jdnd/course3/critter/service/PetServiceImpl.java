package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.domain.Customer;
import com.udacity.jdnd.course3.critter.domain.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public Customer getOwnerByPetId(Long id) {
        return this.petRepository.findOwnerByPetId(id);
    }

    @Override
    public Pet save(Pet p)
    {

        return this.petRepository.save(p);
    }

    @Override
    public Pet findById(Long id) {
        return this.petRepository.findById(id).get();
    }

    @Override
    public List<Pet> findAll() {
        return this.petRepository.findAll();
    }

    @Override
    public List<Pet> findAllByOwnerId(Long ownerId) {
        return this.petRepository.findAllByOwnerId(ownerId);
    }
}
