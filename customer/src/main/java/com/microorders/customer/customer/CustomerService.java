package com.microorders.customer.customer;

import com.microorders.customer.order.IOrder;
import com.microorders.customer.order.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class CustomerService implements ICustomer {
    private final CustomerRepository customerRepository;
    private final IOrder iOrder;

    public CustomerService(CustomerRepository customerRepository, IOrder iOrder) {
        this.customerRepository = customerRepository;
        this.iOrder = iOrder;
    }

    public Customer createCustomer(Customer customer) throws CustomerPhoneNumberTakenException {
        // Check that the phone number is not already taken
        Optional<Customer> customerWithSamePhoneNumber = customerRepository
                .findCustomerByPhoneNumber(customer.getPhoneNumber());
        if (customerWithSamePhoneNumber.isPresent()) {
            throw new CustomerPhoneNumberTakenException();
        }

        return customerRepository.save(customer);
    }

    public Customer findByPhoneNumber(String phoneNumber) throws CustomerNotFoundException {
        return customerRepository
                .findCustomerByPhoneNumber(phoneNumber)
                .orElseThrow(CustomerNotFoundException::new);
    }

    public Customer findCustomerById(Long id) throws CustomerNotFoundException {
        return this.customerRepository
                .findById(id)
                .orElseThrow(CustomerNotFoundException::new);
    }

    public List<Order> listPreviousOrder(Long id) throws CustomerNotFoundException {
        Customer customer = this.customerRepository
                .findById(id)
                .orElseThrow(CustomerNotFoundException::new);

        return iOrder.listCustomerPreviousOrder(customer);
    }

    public List<Customer> listCustomers() {
        return customerRepository.findAll();
    }
}
