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

}
