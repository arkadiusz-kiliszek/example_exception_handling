package com.example.example_exception_handling.mappers;

import com.example.example_exception_handling.dto.CustomerDto;
import com.example.example_exception_handling.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerDtoMapper {
    CustomerDtoMapper INSTANCE = Mappers.getMapper(CustomerDtoMapper.class);

    Customer map(CustomerDto customerDto);

    CustomerDto map(Customer customer);
}
