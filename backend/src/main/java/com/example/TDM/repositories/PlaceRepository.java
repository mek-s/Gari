package com.example.TDM.repositories;

import com.example.TDM.models.Parking;
import com.example.TDM.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {

    @Query(value = "SELECT p FROM place p WHERE parking.id_parking =:id")
    List<Place> findAllByParkingId(@Param("id") Integer id_parking);
}
