package com.microorder.order_services.order;
import
import com.microorder.order_services.customer.Customer;
import com.microorder.order_services.truck.Truck;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private int truckId;

    public Order(Long customerId, int truckId){
        this.customerId = customerId;
        this.truckId = truckId;
    }
}
