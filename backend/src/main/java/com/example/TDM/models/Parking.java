package com.example.TDM.models;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnCloudPlatform;

import java.util.Set;


@Entity
@Table(name = "parking")
public class Parking {
    @Id
    @SequenceGenerator(
            name = "parking_sequence",
            sequenceName = "parking_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "parking_sequence"
    )
    @Column(name = "id_parking")
    private Integer id_parking;

    @Column(name = "name")
    private String name;

    @Column(name = "commune")
    private String commune;

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "nb_places")

    private Integer  nb_places;

    @Column(name = "latitude")
    private  Double latitude;


    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "tarif")
    private  Double tarif;
    @Column(name = "image")
    private String image;

    public Integer getIdParking() {
        return id_parking;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public Integer getNb_places() {
        return nb_places;
    }

    public void setNb_places(Integer nb_places) {
        this.nb_places = nb_places;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getTarif() {
        return tarif;
    }

    public void setTarif(Double tarif) {
        this.tarif = tarif;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



}
