// Arnold
package com.example.pfi_philipk_arnoldu;

/**
 * Classe représentant un client du commerce électronique
 * @author Arnold Uzabakiriho
 * @author Philip Kvaratshelya
 */
public class Client {
    private String nom;
    private String motDePasse;

    public Client(String nom, String motDePasse) {
        this.nom = nom;
        this.motDePasse = motDePasse;
    }
    // get set
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
