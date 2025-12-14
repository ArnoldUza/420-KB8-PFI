// Arnold details des produits
package com.example.pfi_philipk_arnoldu;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pfi_philipk_arnoldu.databinding.ActivityDetailProduitBinding;

public class DetailProduitActivity extends AppCompatActivity {

    private ActivityDetailProduitBinding binding;
    private MediaPlayer mpDetail;
    private Produit produit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailProduitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        produit = (Produit) getIntent().getSerializableExtra("produit");

        if (produit != null) {
            binding.tvDetailNom.setText(produit.getNom());
            binding.tvDetailPrix.setText(String.format("%.2f $", produit.getPrix()));
            binding.tvDetailDescription.setText(produit.getDescription());
            binding.imgDetailProduit.setImageResource(produit.getImageRessource());
        }

        animerImage();

        Uri uriVideo = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_produit);
        binding.videoDetail.setVideoURI(uriVideo);
        binding.videoDetail.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            binding.videoDetail.start();
        });

        mpDetail = MediaPlayer.create(this, R.raw.musique_detail);
        if (mpDetail != null) {
            mpDetail.setLooping(true);
            mpDetail.start();
        }

        binding.btnAjouterPanier.setOnClickListener(v -> ajouterAuPanier());
    }

    private void animerImage() {
        ScaleAnimation scaleAnim = new ScaleAnimation(
                0.8f, 1.0f,
                0.8f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(Animation.INFINITE);
        scaleAnim.setRepeatMode(Animation.REVERSE);
        binding.imgDetailProduit.startAnimation(scaleAnim);
    }

    private void ajouterAuPanier() {
        PanierSingleton.getInstance().ajouterProduit(produit);

        binding.btnAjouterPanier.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(200)
                .withEndAction(() -> {
                    binding.btnAjouterPanier.animate()
                            .scaleX(1.0f)
                            .scaleY(1.0f)
                            .setDuration(200)
                            .start();
                })
                .start();

        Toast.makeText(
                this,
                produit.getNom() + " ajouté au panier",
                Toast.LENGTH_SHORT
        ).show();

        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mpDetail != null && mpDetail.isPlaying()) {
            mpDetail.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mpDetail != null && !mpDetail.isPlaying()) {
            mpDetail.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mpDetail != null) {
            mpDetail.stop();
            mpDetail.release();
            mpDetail = null;
        }
    }
}
