package com.example.TDM.models;

import jakarta.persistence.*;

@Entity
@Table(name = "parking")
public class Parking {
    @Id
    private Integer idParking;

    private String name;

    private String commune;


    private Integer  nbPlaces;


    private  Double latitude;


    private Double longitude;


    private  Double tarif;

    private String image;

}
