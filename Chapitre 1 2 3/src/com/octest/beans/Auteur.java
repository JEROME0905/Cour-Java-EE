package com.octest.beans;

public class Auteur {
    // Déclaration des attributs
    private String nom;
    private String prenom;
    private boolean actif;

    // Déclarations des getters

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public boolean isActif() {
        return actif;
    }

    // Déclarations des setters

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
}
