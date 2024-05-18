package com.example.TDM.controllers;

import com.example.TDM.models.Parking;
import com.example.TDM.models.Place;
import com.example.TDM.repositories.PlaceRepository;
import com.example.TDM.services.ParkingService;
import com.example.TDM.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

   @GetMapping("/parking/{id}")
   public List<Place> getAllParkingPlaces(@PathVariable Integer id){return  this.placeService.getParkingPlaces(id);}

    @GetMapping("/unreserved/random")
    public Integer getRandomUnreservedPlaceId() {
        List<Place> unreservedPlaces = this.placeService.getUnreservedPlaces();
        if (!unreservedPlaces.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(unreservedPlaces.size());
            return unreservedPlaces.get(randomIndex).getId_place();
        } else {
            // Handle case where there are no unreserved places
            return null;
        }
    }


}
