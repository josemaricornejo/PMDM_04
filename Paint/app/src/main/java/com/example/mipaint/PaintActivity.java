package com.example.mipaint;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

import static com.example.mipaint.Lienzo.current_brush;

public class PaintActivity extends AppCompatActivity {
    //La clase Path guarda los segmentos del trazo que hacemos con el dedo
    public static Path path = new Path();
    //Es el pincel que podemos configurar para pintar,
    public static Paint paint_brush = new Paint();

    public static boolean brush = false;
    public static boolean start = false;
    public static boolean face = false;


    Lienzo lienzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        lienzo = new Lienzo(this);
    }


    //Evento asociado al botón rojo para cambiar de color el pincel
    public void redColor(View view) {
        //Le damos un color al pincel
        paint_brush.setColor(Color.RED);
        currentColor(paint_brush.getColor());
        brush = true;
        start = false;
        face = false;

    }

    public void greenColor(View view) {
        paint_brush.setColor(Color.GREEN);
        currentColor(paint_brush.getColor());
        brush = true;
        start = false;
        face = false;

    }

    public void yellowColor(View view) {
        paint_brush.setColor(Color.YELLOW);
        currentColor(paint_brush.getColor());
        brush = true;
        start = false;
        face = false;

    }

    public void blueColor(View view) {
        paint_brush.setColor(Color.BLUE);
        currentColor(paint_brush.getColor());
        brush = true;
        start = false;
        //face=false;

    }

    public void starBrush(View view) {
        brush = false;
        start = true;
        face = false;
    }


    public void faceBrush(View view) {
        brush = false;
        start = false;
        face = true;
    }


    //Creamos un nuevo trazado para el color seleccionado
    public void currentColor(int c) {
        //Color seleccionado para el pincel
        current_brush = c;
        path = new Path();
    }


    public void onClickVolver(View view) {
        finish();

    }

    public void onClickBorrar(View view) {
        Lienzo.deleteCanvas();
    }
}