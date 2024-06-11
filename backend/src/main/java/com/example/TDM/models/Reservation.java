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

    @Column(name = "id_parking")
    private Integer idParking; // Change to camel case

    @Column(name = "date")
    private String date;

    @Column(name = "heure_entree")
    private  String heureEntree;

    @Column(name = "heure_sortie")
    private String heure_sortie;

    @Column(name = "code_qr")
    private String codeQr;

    @Column(name = "prix")
    private Double prix;

    @Column(name = "username")
    private String username;

    // Getters and Setters

    public Integer getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(Integer id_reservation) {
        this.id_reservation = id_reservation;
    }

    public Integer getId_place() {
        return id_place;
    }

    public void setId_place(Integer id_place) {
        this.id_place = id_place;
    }

    public Integer getIdParking() {
        return idParking;
    }

    public void setIdParking(Integer idParking) {
        this.idParking = idParking;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeureEntree() {
        return heureEntree;
    }

    public void setHeureEntree(String heure_entree) {
        this.heureEntree = heure_entree;
    }
    public String getHeure_sortie() {
        return heure_sortie;
    }

    public void setHeure_sortie(String heure_sortie) {
        this.heure_sortie = heure_sortie;
    }

    public String getCodeQr() {
        return codeQr;
    }

    public void setCodeQr(String codeQr) {
        this.codeQr = codeQr;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
