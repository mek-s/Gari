package com.example.TDM.controllers;

import com.example.TDM.models.Parking;
import com.example.TDM.models.Place;
import com.example.TDM.repositories.PlaceRepository;
import com.example.TDM.services.ParkingService;
import com.example.TDM.services.PlaceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/all")
    public List<Place> getAllPlaces() {
        return placeService.getAllPlaces();
    }

    @GetMapping("/parking/{id}")
    public List<Place> getAllParkingPlaces(@PathVariable Integer id) {
        return this.placeService.getParkingPlaces(id);
    }

    @GetMapping("/unreserved/random/{parkingId}")
    public Integer getRandomUnreservedPlaceId(@PathVariable Integer parkingId) {
        List<Place> unreservedPlaces = this.placeService.getUnreservedPlacesForParking(parkingId);

        if (!unreservedPlaces.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(unreservedPlaces.size());
            return unreservedPlaces.get(randomIndex).getId_place();
        } else {

            return null;
        }
    }
    @PostMapping("/reserve/{id}")
    public void reservePlace(@PathVariable Integer id) {
        placeService.reservePlace(id);
    }
}
