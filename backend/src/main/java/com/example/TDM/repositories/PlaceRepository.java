package com.example.TDM.repositories;

import com.example.TDM.models.Parking;
import com.example.TDM.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {

    List<Place> findByParking(Integer id);
    List<Place> findByReserveeFalse();
    List<Place> findByParkingAndReserveeFalse(Integer id);

    @Modifying
    @Query("UPDATE Place p SET p.reservee = TRUE WHERE p.id_place = :id_place")
    void reservePlace(@Param("id_place") Integer id_place);
}

