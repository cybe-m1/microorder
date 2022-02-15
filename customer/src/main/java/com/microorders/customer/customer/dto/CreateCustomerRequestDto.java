package com.microorders.customer.customer.dto;

import lombok.Data;

@Data
public class CreateCustomerRequestDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
