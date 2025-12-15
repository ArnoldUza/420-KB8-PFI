// Philip page de confirmation

package com.example.pfi_philipk_arnoldu;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.pfi_philipk_arnoldu.databinding.ActivityConfirmationBinding;
/**
 * Activité de confirmation affichée après une commande réussie
 * @author Arnold Uzabakiriho
 * @author Philip Kvaratshelya
 */
public class ConfirmationActivity extends AppCompatActivity {

    private ActivityConfirmationBinding binding;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_confirmation);

        String nomClient = getIntent().getStringExtra("nomClient");
        if (nomClient != null) {
            binding.txtMerci.setText(getString(R.string.merci_client, nomClient));
        }

        Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        binding.imgCheck.startAnimation(bounce);

        Uri uriVideo = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_confirmation);
        binding.videoConfirm.setVideoURI(uriVideo);
        binding.videoConfirm.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            binding.videoConfirm.start();
        });

        mp = MediaPlayer.create(this, R.raw.musique_confirmation);
        mp.setLooping(true);
        mp.start();

        binding.btnRetour.setOnClickListener(v -> {
            Toast.makeText(this, "Retour au menu", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ListeProduitsActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mp != null && mp.isPlaying()) {
            mp.pause();
        }
        if (binding.videoConfirm.isPlaying()) {
            binding.videoConfirm.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mp != null && !mp.isPlaying()) {
            mp.start();
        }
        binding.videoConfirm.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
