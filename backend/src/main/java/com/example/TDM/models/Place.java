package com.example.TDM.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "place")
public class Place {
    @Id
    @Column(name = "idPlace")
    private Integer id_place ;
    private  Integer idParking;
    private Boolean reservee;
}
