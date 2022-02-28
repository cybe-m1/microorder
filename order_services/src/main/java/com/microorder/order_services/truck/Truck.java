package com.microorder.order_services.truck;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Truck {
    private int id_truck;
    private String name;
    private String description;
    private Integer id_creneau;
    private Integer id_position;
}
