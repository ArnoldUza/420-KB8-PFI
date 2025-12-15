//Philip panier singleton
package com.example.pfi_philipk_arnoldu;

import java.util.ArrayList;

/**
 * Singleton qui gère le panier d'achat partagé entre toutes les activités
 * @author Arnold Uzabakiriho
 * @author Philip Kvaratshelya
 */
public class PanierSingleton {

    private static PanierSingleton instance;
    private final ArrayList<Produit> produits;

    private PanierSingleton() {
        produits = new ArrayList<>();
    }

    public static PanierSingleton getInstance() {
        if (instance == null) {
            instance = new PanierSingleton();
        }
        return instance;
    }

    public void ajouterProduit(Produit p) {
        produits.add(p);
    }

    public ArrayList<Produit> getProduits() {
        return produits;
    }

    public void vider() {
        produits.clear();
    }
}
