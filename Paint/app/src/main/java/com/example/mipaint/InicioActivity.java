package com.example.mipaint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class InicioActivity extends AppCompatActivity {
    MediaPlayer mySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        mySong = MediaPlayer.create(InicioActivity.this, R.raw.avengers);
        mySong.start();
    }

    public void onClick(View view) {
        Intent miIntent = new Intent(InicioActivity.this, PaintActivity.class);
        startActivity(miIntent);
        mySong.stop();

    }

}