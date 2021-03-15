package com.example.mipaint;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

import static com.example.mipaint.Display.current_brush;

public class PaintActivity extends AppCompatActivity {
    //La clase Path guarda los segmentos del trazo que hacemos con el dedo
    public static Path path = new Path();
    //Es el pincel que podemos configurar para pintar,
    public static Paint paint_brush = new Paint();

    //Pincel Estrella
    public static Paint star_brush = new Paint();
    public static Path star_path = new Path();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
    }

    //Evento asociado al botón rojo para cambiar de color el pincel
    public void redColor(View view) {
        //Le damos un color al pincel
        paint_brush.setColor(Color.RED);
        currentColor(paint_brush.getColor());
    }

    public void greenColor(View view) {
        paint_brush.setColor(Color.GREEN);
        currentColor(paint_brush.getColor());
    }

    public void yellowColor(View view) {
        paint_brush.setColor(Color.YELLOW);
        currentColor(paint_brush.getColor());
    }

    public void blueColor(View view) {
        paint_brush.setColor(Color.BLUE);
        currentColor(paint_brush.getColor());
    }

    public void starBrush(View view){

    }

    //Creamos un nuevo trazado para el color seleccionado
    public void currentColor(int c){
        //Color seleccionado para el pincel
        current_brush = c;
        path = new Path();
    }

}