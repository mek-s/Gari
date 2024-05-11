package com.example.TDM.controllers;

import com.example.TDM.models.Parking;
import com.example.TDM.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parkings")
public class ParkingController {

    private final ParkingService parkingService;

    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService=parkingService;
    }

    @GetMapping("/all")
    public List<Parking> getAllParkings() {
        return parkingService.getAllParkings();
    }

    @GetMapping("/{id}")
    public Optional<Parking> getParkingById(@PathVariable Integer id){
        return parkingService.getParkingById(id);
    }


}
