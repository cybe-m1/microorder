package com.microorders.customer.customer;

import com.microorders.customer.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByPhoneNumber(String phoneNumber);
}
