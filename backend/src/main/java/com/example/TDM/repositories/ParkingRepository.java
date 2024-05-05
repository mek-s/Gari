package com.example.TDM.repositories;

import com.example.TDM.models.Parking;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParkingRepository extends JpaRepository<Parking, Integer> {
}
