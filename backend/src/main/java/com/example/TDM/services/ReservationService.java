package com.example.TDM.services;

import com.example.TDM.models.Reservation;
import com.example.TDM.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(Reservation reservation) {
        // Add any additional logic here before saving the reservation
        return reservationRepository.save(reservation);
    }


}
