package com.example.example_exception_handling.endpoint;

import com.example.example_exception_handling.dto.CustomerDto;
import com.example.example_exception_handling.exception.CustomerAlreadyExistsException;
import com.example.example_exception_handling.handlers.ErrorResponse;
import com.example.example_exception_handling.model.Customer;
import com.example.example_exception_handling.serivces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // Get Customer by Id
    @GetMapping("/getCustomer/{id}")
    public CustomerDto getCustomer(@PathVariable("id") Long id) {
        return customerService.getCustomer(id);
    }

    // Add new Customer
    @PostMapping("/addCustomer")
    public String addcustomer(@RequestBody CustomerDto customerDto) {
        return customerService.addCustomer(customerDto);
    }

    // Update Customer details
    @PutMapping("/updateCustomer")
    public String updateCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.updateCustomer(customerDto);
    }

    @ExceptionHandler(value = CustomerAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCustomerAlreadyExistsException(
            CustomerAlreadyExistsException ex) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(),
                ex.getMessage());
    }
}