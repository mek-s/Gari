package com.example.TDM.services;

import com.example.TDM.models.Parking;
import com.example.TDM.models.Place;
import com.example.TDM.repositories.ParkingRepository;
import com.example.TDM.repositories.PlaceRepository;
import jakarta.transaction.Transactional;
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

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public List<Place> getParkingPlaces(Integer id){
        return this.placeRepository.findByParking(id);
    }


    public List<Place> getUnreservedPlacesForParking(Integer parkingId) {
        return placeRepository.findByParkingAndReserveeFalse(parkingId);
    }


    @Transactional
    public void reservePlace(Integer id_place) {
        placeRepository.reservePlace(id_place);
    }
}

