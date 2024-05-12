package com.example.TDM.models;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Integer id_reservation;



    @Column(name = "id_place")
    private Integer id_place;

    @Column(name = "date")
    private Date date;

    @Column(name = "heure_entree")
    private Time heure_entree; // Changed data type to Time

    @Column(name = "heure_sortie")
    private Time heure_sortie; // Changed data type to Time

    @Column(name = "code_qr")
    private String code_qr; // Changed data type to String

    @Column(name = "prix")
    private Double prix;


    public Integer getId_place() {
        return id_place;
    }

    public Date getDate() {
        return date;
    }

    public Time getHeure_entree() {
        return heure_entree;
    }

    public Time getHeure_sortie() {
        return heure_sortie;
    }

    public String getCode_qr() {
        return code_qr;
    }

    public Double getPrix() {
        return prix;
    }
}
