package com.example.TDM.controllers;

import com.example.TDM.models.Reservation;
import com.example.TDM.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        try {
            Reservation createdReservation = reservationService.createReservation(reservation);
            return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error processing reservation request: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/byUsername/{username}")
    public ResponseEntity<List<Reservation>> getReservationsByUsername(@PathVariable String username) {
        try {
            List<Reservation> reservations = reservationService.getReservationsByUsername(username);
            if (reservations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error retrieving reservations by username: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Integer id) {
        Optional<Reservation> reservation = reservationService.getReservationById(id);
        return reservation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/byQRCode/{code_qr}")
    public ResponseEntity<Reservation> getReservationByQRCode(@PathVariable String code_qr) {
        Reservation reservation = reservationService.getReservationByQRCode(code_qr);
        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // New endpoint to get reservations by parking ID
    @GetMapping("/byParkingId/{id_parking}")
    public ResponseEntity<List<Reservation>> getReservationsByParkingId(@PathVariable Integer id_parking) {
        try {
            List<Reservation> reservations = reservationService.getReservationsByParkingId(id_parking);
            if (reservations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error retrieving reservations by parking ID: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
