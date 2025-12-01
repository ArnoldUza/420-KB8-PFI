
package com.example.pfi_philipk_arnoldu;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pfi_philipk_arnoldu.Client;

/**
 * Activité de connexion pour l'application e-commerce
 * @author Arnold Uzabakiriho
 * @author Philip Kvaratshelya
 */
public class ConnexionActivity extends AppCompatActivity {

    private EditText txtNom;
    private EditText txtMotDePasse;
    private Button btnConnexion;
    private VideoView videoConnexion;
    private MediaPlayer mpConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        // Initialisation des vues
        txtNom = findViewById(R.id.txtNom);
        txtMotDePasse = findViewById(R.id.txtMotDePasse);
        btnConnexion = findViewById(R.id.btnConnexion);
        videoConnexion = findViewById(R.id.videoConnexion);

        // Configuration de la vidéo
        Uri uriVideo = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_connexion);
        videoConnexion.setVideoURI(uriVideo);
        videoConnexion.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            videoConnexion.start();
        });

        // Musique de fond
        mpConnexion = MediaPlayer.create(this, R.raw.musique_connexion);
        mpConnexion.setLooping(true);
        mpConnexion.start();

        // Bouton de connexion
        btnConnexion.setOnClickListener(v -> {
            String nom = txtNom.getText().toString().trim();
            String motDePasse = txtMotDePasse.getText().toString().trim();

            // Validation simple
            if (nom.isEmpty() || motDePasse.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            // Créer un objet Client
            Client client = new Client(nom, motDePasse);

            // Passer à l'activité suivante (liste des produits)
            Intent intent = new Intent(ConnexionActivity.this, ListeProduitsActivity.class);
            intent.putExtra("nomClient", client.getNom());
            startActivity(intent);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mpConnexion != null && mpConnexion.isPlaying()) {
            mpConnexion.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mpConnexion != null && !mpConnexion.isPlaying()) {
            mpConnexion.start();
        }
        if (videoConnexion != null) {
            videoConnexion.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mpConnexion != null) {
            mpConnexion.stop();
            mpConnexion.release();
            mpConnexion = null;
        }
    }
}