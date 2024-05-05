package com.example.TDM.repositories;

import com.example.TDM.models.Parking;
import com.example.TDM.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
}
