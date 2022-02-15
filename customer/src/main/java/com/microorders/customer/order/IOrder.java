package com.microorders.customer.order;

import com.microorders.customer.customer.Customer;

import java.util.List;

public interface IOrder {
    List<Order> listCustomerPreviousOrder(Customer customer);
}
