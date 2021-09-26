package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.domain.Customer;
import com.udacity.jdnd.course3.critter.domain.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        if(petDTO.getOwnerId()<=0)
        {
            throw new IllegalStateException("Pet owner id must not be null");
        }

        Customer c = this.customerService.findById(petDTO.getOwnerId());
        Pet p = new Pet();
        petDTO.toPet(p,c);
        p = this.petService.save(p);
        petDTO.setId(p.getId());
        return petDTO;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {

        PetDTO petDTO = new PetDTO();
        Pet p = this.petService.findById(petId);
        if(p!=null)
        {
            petDTO.fromPet(p);
        }
        return petDTO;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = this.petService.findAll();
        List<PetDTO> dtos = pets.stream().map(x->{
            PetDTO dto = new PetDTO();
            dto.fromPet(x);
            return dto;
        }).collect(Collectors.toList());
        return dtos;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = this.petService.findAllByOwnerId(ownerId);
        List<PetDTO> dtos = pets.stream().map(x->{
            PetDTO dto = new PetDTO();
            dto.fromPet(x);
            return dto;
        }).collect(Collectors.toList());

        return dtos;
    }
}
