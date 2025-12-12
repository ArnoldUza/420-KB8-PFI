package com.example.pfi_philipk_arnoldu;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import com.example.pfi_philipk_arnoldu.databinding.ActivityListeProduitsBinding;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;

public class ListeProduitsActivity extends AppCompatActivity{

    private ActivityListeProduitsBinding binding;
    private ArrayList<Produit> listeProduits;
    private ProduitsAdapter adapter;
    private MediaPlayer mpListe;
    private String nomClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_produits);

        //Récupérer le nom du client
        nomClient = getIntent().getStringExtra("nomClient");
        if(nomClient != null){
            setTitle("Bienvenue " + nomClient + "!");
        }

        binding.recyclerProduits.setLayoutManager(new GridLayoutManager(this, 2));

        //Créer la liste de produits
        creerListeProduits();

        //Adapter
        adapter = new ProduitsAdapter(listeProduits, this);
        binding.recyclerProduits.setAdapter(adapter);

        //Musique de fond
        mpListe = MediaPlayer.create(this, R.raw.musique_liste);
        if(mpListe != null) {
            mpListe.setLooping(true);
            mpListe.start();
        }
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

    @Overrride
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_panier) {
            Intent intent = new Intent(this, PanierActivity.class);
            intent.putExtra("nomClient", nomClient);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
