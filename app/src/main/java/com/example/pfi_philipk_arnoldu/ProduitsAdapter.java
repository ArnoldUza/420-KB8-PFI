// Arnold adaptateur recycleur produits
package com.example.pfi_philipk_arnoldu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pfi_philipk_arnoldu.databinding.ItemProduitBinding;
import java.util.ArrayList;

public class ProduitsAdapter extends RecyclerView.Adapter<ProduitsAdapter.ViewHolder> {

    private ArrayList<Produit> produits;
    private Context context;

    public ProduitsAdapter(ArrayList<Produit> produits, Context context) {
        this.produits = produits;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProduitBinding binding = ItemProduitBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produit produit = produits.get(position);

        holder.binding.tvNomProduit.setText(produit.getNom());
        holder.binding.tvPrixProduit.setText(String.format("%.2f $", produit.getPrix()));
        holder.binding.imgProduit.setImageResource(produit.getImageRessource());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailProduitActivity.class);
            intent.putExtra("produit", produit);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return produits.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemProduitBinding binding;

        public ViewHolder(ItemProduitBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}