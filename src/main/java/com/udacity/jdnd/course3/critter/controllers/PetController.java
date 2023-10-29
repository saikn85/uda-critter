package com.udacity.jdnd.course3.critter.controllers;

import com.udacity.jdnd.course3.critter.dao.PetService;
import com.udacity.jdnd.course3.critter.dtos.PetDTO;
import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.util.pet.PetMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetService _petSvc;

    public PetController(PetService petSvc) {
        _petSvc = petSvc;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        try {
            Pet pet = _petSvc.save(PetMapper.mapDtoToEntity(petDTO), petDTO.getOwnerId());
            return PetMapper.mapEntityToDto(pet, petDTO.getOwnerId());
        } catch (Exception ex) {
            System.out.println("savePet failed : " + ex.getMessage());
            return new PetDTO();
        }
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        try {
            Customer customer = _petSvc.findPetById(petId);
            return customer.getPets().stream()
                    .map(pet -> PetMapper.mapEntityToDto(pet, customer.getId()))
                    .findFirst().orElse(new PetDTO());
        } catch (Exception ex) {
            System.out.println("getPet failed : " + ex.getMessage());
            return new PetDTO();
        }
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        try {
            Customer customer = _petSvc.findPetsByOwnerId(ownerId);
            return customer.getPets().stream()
                    .map(pet -> PetMapper.mapEntityToDto(pet, customer.getId()))
                    .sorted(Comparator.comparing(p -> p.getId()))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            System.out.println("getPetsByOwner pet failed : " + ex.getMessage());
            return new ArrayList<>();
        }
    }
}
