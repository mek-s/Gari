package com.example.TDM.models;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "photo")
    private String photo;

    @Column(name = "token") // Add token column
    private String token;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(String username, String password, String nom, String prenom, String photo, String token) {
        this.username = username;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.token = token; // Initialize token in constructor
    }

    // Getters and setters for username, password, nom, prenom, photo, and token
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}