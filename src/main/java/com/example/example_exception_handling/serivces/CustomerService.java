package com.example.example_exception_handling.serivces;

import com.example.example_exception_handling.dto.CustomerDto;
import com.example.example_exception_handling.exception.CustomerAlreadyExistsException;
import com.example.example_exception_handling.exception.NoSuchCustomerExistsException;
import com.example.example_exception_handling.model.Customer;
import com.example.example_exception_handling.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // Method to get customer by Id.Throws
    // NoSuchElementException for invalid Id
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("NO CUSTOMER PRESENT WITH ID = " + id));
    }

    // Method to add new customer details to database.Throws
    // CustomerAlreadyExistsException when customer detail
    // already exist
    public String addCustomer(CustomerDto customer) {
        boolean customerExists = customerRepository.existsByName(customer.getName());
        if (!customerExists) {
            Customer customerNew = new Customer();
            customerNew.setName(customer.getName());
            customerNew.setAddress(customer.getAddress());
            customerRepository.save(customerNew);
            return "Customer added successfully";
        } else throw new CustomerAlreadyExistsException("Customer already exists!!");
    }

    // Method to update customer details to database.Throws
    // NoSuchCustomerExistsException when customer doesn't
    // already exist in database
    public String updateCustomer(CustomerDto customer) {
        Customer existingCustomer = customerRepository.findById(customer.getId()).orElse(null);
        if (existingCustomer == null) throw new NoSuchCustomerExistsException("No Such Customer exists!!");
        else {
            existingCustomer.setName(customer.getName());
            existingCustomer.setAddress(customer.getAddress());
            customerRepository.save(existingCustomer);
            return "Record updated Successfully";
        }
    }
}
