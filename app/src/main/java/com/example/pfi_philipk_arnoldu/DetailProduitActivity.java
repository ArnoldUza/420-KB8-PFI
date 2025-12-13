//Arnold Uzabakiriho
package com.example.pfi_philipk_arnoldu;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.pfi_philipk_arnoldu.databinding.ActivityDetailProduitBinding;

/**
 * Activité qui affiche les détails d'un produit
 * Permet d'ajouter le produit au panier avec notification
 *
 * @author Philip Kvaratshelya
 * @author Arnold Uzabakiriho
 */

public class DetailProduitActivity extends AppCompatActivity {

    private ActivityDetailProduitBinding binding;
    private MediaPlayer mpDetail;
    private Produit produit;
    private static final String CHANNEL_ID = "panier_channel";
    private static final int NOTIF_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailProduitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Récupérer le produit
        produit = (Produit) getIntent().getSerializableExtra("produit");

        if (produit != null) {
            binding.tvDetailNom.setText(produit.getNom());
            binding.tvDetailPrix.setText(String.format("%.2f", produit.getPrix()));
            binding.tvDetailDescription.setText(produit.getDescription());
            binding.imgDetailProduit.setImageResource(produit.getImageRessource());
        }

        // Animation sur l'image
        animerImage();

        //Video de demo
        Uri uriVideo = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_produit);
        binding.videoDetail.setVideoUri(uriVideo);
        binding.videoDetail.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            binding.videoDetail.start();
        });

        //Musique de fond
        mpDetail = MediaPlayer.create(this, R.raw.musique_detail);
        if (mpDetail != null) {
            mpDetail.setLooping(true);
            mpDetail.start();
        }

        //Créer le canal de notification
        creerCanalNotification();

        //Bouton ajouter au panier
        binding.btnAjouterPanier.setOnClickListener(v -> ajouterAuPanier());

        /**
         * Anime l'image du produit avec un effet de zoom
         */
        private void animerImage () {
            ScaleAnimation scaleAnim = new ScaleAnimation(
                    0.8f, 1.0f, 0.8f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
            );
            scaleAnim.setDuration(1000);
            scaleAnim.setRepeatCount(Animation.INFINITE);
            scaleAnim.setRepeatMode(Animation.REVERSE);
            binding.imgDetailProduit.startAnimation(scaleAnim);
        }

        /**
         * Crée le canal de notification pour Android 8+
         */
        private void creerCanalNotification () {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES .0){
                CharSequence nom = "Panier";
                String description = "Notifications du panier";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, nom, importance);
                channel.setDescription(description);

                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
        }

        /**
         * Ajoute le produit au panier et envoie une notification
         */
        private void ajouterAuPanier () {
            PanierSingleton.getInstance().ajouterProduit(produit);

            //Animation du bouton
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

            //Notification
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_menu_add)
                    .setContentTitle("Produit ajouté!")
                    .setContentText(produit.getNom() + " ajouté au panier")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(NOTIF_ID, builder.build());

            finish();
        }

        @Override
        protected void onPause () {
            super.onPause();
            if (mpDetail != null && mpDetail.isPlaying()) {
                mpDetail.pause();
            }
        }

        @Override
        protected void onResume () {
            super.onResume();
            if (mpDetail != null && !mpDetail.isPlaying()) {
                mpDetail.start();
            }
        }

        @Override
        protected void onDestroy () {
            super.onDestroy();
            if (mpDetail != null) {
                mpDetail.stop();
                mpDetail.release();
                mpDetail = null;
            }
        }
    }
}
