package com.udacity.jdnd.course3.critter.util.user;

import com.udacity.jdnd.course3.critter.dtos.CustomerDTO;
import com.udacity.jdnd.course3.critter.entities.Customer;

public class CustomerMapper {
    public static Customer mapDtoToEntity(CustomerDTO dto){
        return new Customer(dto.getName(), dto.getPhoneNumber());
    }

    public static CustomerDTO mapEntityToDto(Customer customer){
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setPhoneNumber(customer.getPhoneNumber());
        return dto;
    }
}
