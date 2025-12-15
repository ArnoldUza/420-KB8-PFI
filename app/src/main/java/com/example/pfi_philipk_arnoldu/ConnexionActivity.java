// Arnold page de connexion

package com.example.pfi_philipk_arnoldu;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.pfi_philipk_arnoldu.databinding.ActivityConnexionBinding;
/**
 * Activité de connexion pour l'application e-commerce de sneakers
 * @author Arnold Uzabakiriho
 * @author Philip Kvaratshelya
 */

public class ConnexionActivity extends AppCompatActivity {

    private ActivityConnexionBinding binding;
    private MediaPlayer mpConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_connexion);

        Client client = new Client("", "");
        binding.setClient(client);

        Uri uriVideo = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_connexion);
        binding.videoConnexion.setVideoURI(uriVideo);
        binding.videoConnexion.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            binding.videoConnexion.start();
        });

        mpConnexion = MediaPlayer.create(this, R.raw.musique_connexion);
        mpConnexion.setLooping(true);
        mpConnexion.start();

        binding.btnConnexion.setOnClickListener(v -> {
            if (client.getNom().isEmpty() || client.getMotDePasse().isEmpty()) {
                Toast.makeText(this, R.string.msg_remplir_champs, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, ListeProduitsActivity.class);
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
        if (binding.videoConnexion != null && !binding.videoConnexion.isPlaying()) {
            binding.videoConnexion.start();
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
