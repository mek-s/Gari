package com.example.TDM.models;

import jakarta.persistence.*;

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
    private String date;

    @Column(name = "heure_entree")
    private String heure_entree;

    @Column(name = "heure_sortie")
    private String heure_sortie;

    @Column(name = "code_qr")
    private String code_qr;

    @Column(name = "prix")
    private Double prix;

    @Column(name = "username")
    private String username;


    public String getUsername() {
        return username;
    }

    public Integer getId_place() {
        return id_place;
    }

    public String getDate() {
        return date;
    }

    public String getHeure_entree() {
        return heure_entree;
    }

    public String getHeure_sortie() {
        return heure_sortie;
    }

    public String getCode_qr() {
        return code_qr;
    }

    public Double getPrix() {
        return prix;
    }
}
