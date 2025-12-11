package com.example.pfi_philipk_arnoldu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfi_philipk_arnoldu.databinding.ItemPanierBinding;

import java.util.ArrayList;

public class PanierAdapter extends RecyclerView.Adapter<PanierAdapter.ViewHolder> {

    private final ArrayList<Produit> produits;
    private final Context context;

    public PanierAdapter(ArrayList<Produit> produits, Context context) {
        this.produits = produits;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPanierBinding binding = ItemPanierBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produit p = produits.get(position);

        holder.binding.imgProduit.setImageResource(p.getImageRessource());
        holder.binding.txtNom.setText(p.getNom());
        holder.binding.txtPrix.setText(p.getPrix() + " $");
        holder.binding.txtQuantite.setText(String.valueOf(p.getQuantite()));

        holder.binding.btnPlus.setOnClickListener(v -> {
            p.setQuantite(p.getQuantite() + 1);
            notifyItemChanged(position);
            if (context instanceof PanierActivity) {
                ((PanierActivity) context).recreate();
            }
        });

        holder.binding.btnMoins.setOnClickListener(v -> {
            if (p.getQuantite() > 1) {
                p.setQuantite(p.getQuantite() - 1);
                notifyItemChanged(position);
                if (context instanceof PanierActivity) {
                    ((PanierActivity) context).recreate();
                }
            }
        });

        holder.binding.btnRetirer.setOnClickListener(v -> {
            produits.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, produits.size());
            if (context instanceof PanierActivity) {
                ((PanierActivity) context).recreate();
            }
        });
    }

    @Override
    public int getItemCount() {
        return produits.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemPanierBinding binding;

        public ViewHolder(ItemPanierBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

