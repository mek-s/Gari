package com.example.TDM.services;

import com.example.TDM.models.Place;
import com.example.TDM.models.Reservation;
import com.example.TDM.repositories.PlaceRepository;
import com.example.TDM.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final PlaceRepository placeRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository , PlaceRepository placeRepository) {
        this.reservationRepository = reservationRepository;
        this.placeRepository= placeRepository;
    }

    public Reservation createReservation(Reservation reservation) {

        Place place = placeRepository.getReferenceById(reservation.getId_place());
        place.setReservee(true);
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsByUsername(String username) {
        return reservationRepository.findByUsername(username);
    }

    public Optional<Reservation> getReservationById(Integer id) {
        return reservationRepository.findById(id);
    }

    public Reservation getReservationByQRCode(String codeQr) {
        Optional<Reservation> optionalReservation = reservationRepository.findByCodeQr(codeQr);
        return optionalReservation.orElse(null);
    }

    // New method to get reservations by parking ID
    public List<Reservation> getReservationsByParkingId(Integer id_parking) {
        return reservationRepository.findByIdParking(id_parking);
    }

    public List<Reservation> getReservationsByHeureEntree(String heureEntree) {
        return reservationRepository.findByHeureEntree( heureEntree);
    }
}
