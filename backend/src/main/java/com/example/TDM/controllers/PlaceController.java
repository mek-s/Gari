package com.example.TDM.controllers;

import com.example.TDM.models.Parking;
import com.example.TDM.models.Place;
import com.example.TDM.repositories.PlaceRepository;
import com.example.TDM.services.ParkingService;
import com.example.TDM.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService=placeService;
    }

    @GetMapping("/all")
    public List<Place> getAllPlaces() {
        return placeService.getAllPlaces();
    }
}
