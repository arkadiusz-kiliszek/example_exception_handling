package com.example.example_exception_handling.serivces;

import com.example.example_exception_handling.dto.CustomerDto;
import com.example.example_exception_handling.exception.CustomerAlreadyExistsException;
import com.example.example_exception_handling.exception.NoSuchCustomerExistsException;
import com.example.example_exception_handling.mappers.CustomerDtoMapper;
import com.example.example_exception_handling.model.Customer;
import com.example.example_exception_handling.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRespository;

    // Method to get customer by Id.Throws
    // NoSuchElementException for invalid Id
    public CustomerDto getCustomer(Long id) {
        return customerRespository.findById(id)
                .map(CustomerDtoMapper.INSTANCE::map)
                .orElseThrow(() -> new NoSuchElementException("NO CUSTOMER PRESENT WITH ID = " + id));
    }

    // Method to add new customer details to database.Throws
    // CustomerAlreadyExistsException when customer detail
    // already exist
    public String addCustomer(CustomerDto customerDto) {
        Customer existingCustomer = customerRespository.findById(customerDto.getId()).orElse(null);
        if (existingCustomer == null) {
            final Customer customer = CustomerDtoMapper.INSTANCE.map(customerDto);
            customerRespository.save(customer);
            return "Customer added successfully";
        } else throw new CustomerAlreadyExistsException("Customer already exists!!");
    }

    // Method to update customer details to database.Throws
    // NoSuchCustomerExistsException when customer doesn't
    // already exist in database
    public String updateCustomer(CustomerDto customerDto) {
        Customer existingCustomer = customerRespository.findById(customerDto.getId()).orElse(null);
        if (existingCustomer == null) throw new NoSuchCustomerExistsException("No Such Customer exists!!");
        else {
            existingCustomer.setName(existingCustomer.getName());
            existingCustomer.setAddress(existingCustomer.getAddress());
            customerRespository.save(existingCustomer);
            return "Record updated Successfully";
        }
    }
}
