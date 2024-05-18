package com.example.TDM.services;

import com.example.TDM.models.Parking;
import com.example.TDM.models.Place;
import com.example.TDM.repositories.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    @Autowired
    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }
    public List<Parking> getAllParkings() {return parkingRepository.findAll();}

    public Optional<Parking> getParkingById(Integer id){return parkingRepository.findById(id);}


    public Double getTarifParking(Integer id) {
        Parking prk = parkingRepository.findById(id).orElse(null);
        if (prk != null) {
            return prk.getTarif();
        } else {
            // Handle the case when the parking with the given ID is not found
            return null; // or throw an exception or return a default value
        }
    }

}
