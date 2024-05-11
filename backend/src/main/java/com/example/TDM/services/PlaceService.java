package com.example.TDM.services;

import com.example.TDM.models.Parking;
import com.example.TDM.models.Place;
import com.example.TDM.repositories.ParkingRepository;
import com.example.TDM.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService  {
    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }
    public List<Place> getAllPlaces() {return placeRepository.findAll();}

    public List<Place> getAllPlacesForParking(Integer parkingId) {
        return placeRepository.findAllByParkingId(parkingId);
    }
}
