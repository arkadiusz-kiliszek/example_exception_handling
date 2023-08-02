package com.example.example_exception_handling.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CustomerDto {
    private Long id;
    @NotBlank
    private String name;
    private String address;
}
