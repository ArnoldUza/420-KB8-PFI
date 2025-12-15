// Arnold listes des produits
package com.example.pfi_philipk_arnoldu;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.pfi_philipk_arnoldu.databinding.ActivityListeProduitsBinding;

import java.util.ArrayList;

/**
 * Activité qui affiche la liste des produits disponibles
 * @author Arnold Uzabakiriho
 * @author Philip Kvaratshelya
 */
public class ListeProduitsActivity extends AppCompatActivity {

    private ActivityListeProduitsBinding binding;
    private ArrayList<Produit> listeProduits;
    private ProduitsAdapter adapter;
    private MediaPlayer mpListe;
    private String nomClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListeProduitsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        nomClient = getIntent().getStringExtra("nomClient");
        if (nomClient != null) {
            setTitle("Bienvenue " + nomClient + "!");
        }

        binding.recyclerProduits.setLayoutManager(new GridLayoutManager(this, 2));

        creerListeProduits();

        adapter = new ProduitsAdapter(listeProduits, this);
        binding.recyclerProduits.setAdapter(adapter);

        binding.btnVoirPanier.setOnClickListener(v -> {
            Intent intent = new Intent(this, PanierActivity.class);
            intent.putExtra("nomClient", nomClient);
            startActivity(intent);
        });

        mpListe = MediaPlayer.create(this, R.raw.musique_liste);
        if (mpListe != null) {
            mpListe.setLooping(true);
            mpListe.start();
        }

        binding.btnDeconnexion.setOnClickListener(v -> {
            Intent intent = new Intent(this, ConnexionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void creerListeProduits() {
        listeProduits = new ArrayList<>();

        listeProduits.add(new Produit("Air Jordan 1", 199.99, R.drawable.sneaker1,
                "Sneaker iconique de Michael Jordan"));
        listeProduits.add(new Produit("Nike Dunk Low", 149.99, R.drawable.sneaker2,
                "Style classique et confortable"));
        listeProduits.add(new Produit("Yeezy Boost 350", 299.99, R.drawable.sneaker3,
                "Design moderne et unique"));
        listeProduits.add(new Produit("New Balance 550", 139.99, R.drawable.sneaker4,
                "Confort et style rétro"));
        listeProduits.add(new Produit("Adidas Samba", 119.99, R.drawable.sneaker5,
                "Classique intemporel"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mpListe != null && mpListe.isPlaying()) {
            mpListe.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mpListe != null && !mpListe.isPlaying()) {
            mpListe.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mpListe != null) {
            mpListe.stop();
            mpListe.release();
            mpListe = null;
        }
    }
}
