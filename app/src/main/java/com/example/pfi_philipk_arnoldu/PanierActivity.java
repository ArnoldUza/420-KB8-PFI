package com.example.pfi_philipk_arnoldu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.wear.activity.ConfirmationActivity;

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

        binding.btnCommander.setOnClickListener(v -> {
            Snackbar.make(binding.getRoot(), "Commande réussie !", Snackbar.LENGTH_LONG)
                    .setAction("OK", view -> {})
                    .show();

            Intent intent = new Intent(this, ConfirmationActivity.class);
            startActivity(intent);
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
