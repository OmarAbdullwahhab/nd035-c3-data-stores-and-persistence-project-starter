package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.domain.Customer;
import com.udacity.jdnd.course3.critter.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {

    @Query("select p.owner from Pet p where p.id = :id")
    Customer findOwnerByPetId(Long id);

    @Query("select p from Pet p where p.owner.id = :ownerId")
    List<Pet> findAllByOwnerId(Long ownerId);

}
