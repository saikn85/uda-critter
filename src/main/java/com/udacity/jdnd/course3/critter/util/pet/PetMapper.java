package com.udacity.jdnd.course3.critter.util.pet;

import com.udacity.jdnd.course3.critter.dtos.CustomerDTO;
import com.udacity.jdnd.course3.critter.dtos.PetDTO;
import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;

public class PetMapper {
    public static Pet mapDtoToEntity(PetDTO dto){
        return new Pet(
                dto.getName(),
                dto.getType(),
                dto.getNotes(),
                dto.getBirthDate()
        );
    }

    public static PetDTO mapEntityToDto(Pet pet, long owner_id){
        PetDTO dto = new PetDTO();
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setType(pet.getType());
        dto.setNotes(pet.getNotes());
        dto.setBirthDate(pet.getBirthDate());
        dto.setOwnerId(owner_id);
        return dto;
    }
}
