package com.example.TDM.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "place")
public class Place {

    @Id
    @SequenceGenerator(
            name = "place_sequence",
            sequenceName = "place_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "place_sequence"
    )
    @JsonProperty("id_place")
    @Column(name = "id_place")
    private Integer id_place ;

    @Column(name = "reservee")
    private Boolean reservee;

    @Column(name = "id_parking")
    private Integer parking;


    public Boolean isReservee() {
        return reservee;
    }

    public Integer getId_place() {
        return id_place;
    }

    public Boolean getReservee() {
        return reservee;
    }

    public Integer getParking() {
        return parking;
    }
}
