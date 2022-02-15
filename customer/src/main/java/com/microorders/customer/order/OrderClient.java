package com.microorders.customer.order;

import com.microorders.customer.customer.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderClient implements IOrder {


    @Override
    public List<Order> listCustomerPreviousOrder(Customer customer) {
        return null;
    }
}
