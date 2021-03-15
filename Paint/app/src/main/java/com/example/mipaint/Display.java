package com.example.mipaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import static com.example.mipaint.PaintActivity.paint_brush;
import static com.example.mipaint.PaintActivity.path;

import static com.example.mipaint.PaintActivity.star_brush;
import static com.example.mipaint.PaintActivity.star_path;

public class Display extends View {

    public static ArrayList<Path> pathList = new ArrayList<>();
    public static ArrayList<Integer> colorList = new ArrayList<>();
    public ViewGroup.LayoutParams params;
    //Color inicial por defecto para el pincel
    public static int current_brush = Color.BLACK;

    public Display(Context context) {
        super(context);
        init(context);
    }

    public Display(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Display(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //Iniciamos el pincel y lo configuramos
    private void init(Context context){
        paint_brush.setAntiAlias(true);
        paint_brush.setColor(Color.BLACK);
        paint_brush.setStyle(Paint.Style.STROKE);
        paint_brush.setStrokeCap(Paint.Cap.ROUND);
        paint_brush.setStrokeJoin(Paint.Join.ROUND);
        //Grosor del pincel
        paint_brush.setStrokeWidth(50f);

        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    //Controla los eventos del usuario
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            //Cuando se pulsa con el dedo
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                invalidate();
                return true;
            //Cuando se mueve el dedo
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x,y);
                pathList.add(path);
                colorList.add(current_brush);
                //LLama a repintar el canvas sin parar para que vaya actualizandolo, nos obliga a sobreescrbir el metodo onDraw
                invalidate();
                return true;
            default:
                return false;
        }
    }
    //En este método colocamos lo que vamos a dibujar en el linezo.
    @Override
    protected void onDraw(Canvas canvas) {
        for(int i=0; i<pathList.size(); i++){
            paint_brush.setColor(colorList.get(i));
            //Dibujamos el trazado con canvas.drawPath(Path, Paint).
            canvas.drawPath(pathList.get(i), paint_brush);
            invalidate();
        }
    }
}
