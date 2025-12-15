// Arnold classe d'un produit
package com.example.pfi_philipk_arnoldu;

import java.io.Serializable;
/**
 *  classe d'un produit
 * @author Arnold Uzabakiriho
 * @author Philip Kvaratshelya
 */
public class Produit implements Serializable {
    private String nom;
    private double prix;
    private int imageRessource;
    private String description;
    private int quantite;
    public Produit(String nom, double prix, int imageRessource, String description) {
        this.nom = nom;
        this.prix = prix;
        this.imageRessource = imageRessource;
        this.description = description;
        this.quantite = 1;
    }
    // get set
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getImageRessource() {
        return imageRessource;
    }

    public void setImageRessource(int imageRessource) {
        this.imageRessource = imageRessource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
