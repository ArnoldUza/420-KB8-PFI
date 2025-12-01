// Arnold Uzabakiriho - Classe représentant un produit sneaker
package com.example.pfi_philipk_arnoldu;

import java.io.Serializable;

/**
 * Représente un produit sneaker dans le commerce électronique
 * @author Arnold Uzabakiriho
 * @author Philip Kvaratshelya
 */
public class Produit implements Serializable {
    private String nom;
    private double prix;
    private int imageRessource;
    private String description;
    private int quantite;

    /**
     * Constructeur pour créer un nouveau produit
     * @param nom Le nom du sneaker
     * @param prix Le prix du sneaker
     * @param imageRessource L'ID de ressource de l'image
     * @param description La description du sneaker
     */
    public Produit(String nom, double prix, int imageRessource, String description) {
        this.nom = nom;
        this.prix = prix;
        this.imageRessource = imageRessource;
        this.description = description;
        this.quantite = 1; // Commence à 1 quand ajouté au panier
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
