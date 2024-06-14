package com.example.TDM.repositories;

import com.example.TDM.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByUsername(String username);
    Optional<Reservation> findById(Integer id);
    Optional<Reservation> findByCodeQr(String qrCode);
    List<Reservation> findByIdParking(Integer idParking);
    List<Reservation> findByHeureEntree(String heureEntree);
}

