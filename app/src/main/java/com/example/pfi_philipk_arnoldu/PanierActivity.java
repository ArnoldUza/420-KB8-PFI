// Panier

package com.example.pfi_philipk_arnoldu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.pfi_philipk_arnoldu.databinding.ActivityPanierBinding;
import com.google.android.material.snackbar.Snackbar;

public class PanierActivity extends AppCompatActivity {

    private ActivityPanierBinding binding;
    private PanierAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPanierBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerPanier.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PanierAdapter(PanierSingleton.getInstance().getProduits(), this);
        binding.recyclerPanier.setAdapter(adapter);

        calculerTotal();

        binding.btnVider.setOnClickListener(v -> {
            PanierSingleton.getInstance().vider();
            adapter.notifyDataSetChanged();
            calculerTotal();
        });

        binding.btnRetourProduits.setOnClickListener(v -> {
            finish();
        });

        binding.btnCommander.setOnClickListener(v -> {
            if (PanierSingleton.getInstance().getProduits().isEmpty()) {
                Toast.makeText(this, "Votre panier est vide!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, ConfirmationActivity.class);
                String nomClient = getIntent().getStringExtra("nomClient");
                intent.putExtra("nomClient", nomClient);
                intent.putExtra("panierVide", false);
                startActivity(intent);
                PanierSingleton.getInstance().vider();
                finish();
            }
        });

    }

    public void calculerTotal() {
        new Thread(() -> {
            double total = 0;
            for (Produit p : PanierSingleton.getInstance().getProduits()) {
                total += p.getPrix() * p.getQuantite();
            }
            double finalTotal = total;
            new Handler(Looper.getMainLooper()).post(() -> binding.setTotal(finalTotal));
        }).start();
    }
}
