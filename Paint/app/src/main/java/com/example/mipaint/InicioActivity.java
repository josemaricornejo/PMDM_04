package com.example.mipaint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    public void onClick(View view) {
        Intent miIntent = new Intent(InicioActivity.this, PaintActivity.class);
        startActivity(miIntent);
    }
}