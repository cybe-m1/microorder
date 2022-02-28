package com.microorder.order_services.order;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    //Ajouter la liste des menus + liste des cartes plus tard
    public Order createOrder(Long customerId, int truckId){
        Order order = new Order(customerId, truckId);
        return orderRepository.save(order);
    }

    //Ajout de plat
    //Calcul du total
}
