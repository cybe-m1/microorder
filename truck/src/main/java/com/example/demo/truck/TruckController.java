package com.example.demo.truck;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trucks")
public class TruckController {
    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @GetMapping
    public List<Truck> getAllCreneau(){
        return truckService.getAllTruck();
    }

    @GetMapping("/{idTruck}")
    public Truck getTruck(@PathVariable int idTruck){
        return truckService.getTruck(idTruck);
    }

    @PostMapping
    public Truck addTruck(@RequestBody Truck truck) {
        return truckService.addTruck(truck);
    }

    @PutMapping("/{idTruck}/position/{idPosition}")
    public Truck putPositionToTruck(@PathVariable int idTruck, @PathVariable int idPosition) {
        return truckService.putPositionToTruck(idTruck, idPosition);
    }

    @PutMapping("/{idTruck}/creneau/{idCreneau}")
    public Truck putCreneauToTruck(@PathVariable int idTruck, @PathVariable int idCreneau) {
        return truckService.putCreneauToTruck(idTruck, idCreneau);
    }

    @PutMapping
    public Truck modifyTruck(@RequestBody Truck truck) {
        return truckService.modifyTruck(truck);
    }

    @DeleteMapping("/{idtruck}")
    public String deletePosition(@PathVariable int idtruck) {
        return truckService.suppTruck(idtruck);
    }
}
