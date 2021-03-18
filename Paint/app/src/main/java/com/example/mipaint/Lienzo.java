package com.example.mipaint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.util.List;

import static com.example.mipaint.PaintActivity.paint_brush;
import static com.example.mipaint.PaintActivity.path;
import static com.example.mipaint.PaintActivity.brush;
import static com.example.mipaint.PaintActivity.start;
import static com.example.mipaint.PaintActivity.face;

public class Lienzo extends View {

    public static ArrayList<Path> pathList = new ArrayList<>();
    public static ArrayList<Integer> colorList = new ArrayList<>();
    public ViewGroup.LayoutParams params;
    //Color inicial por defecto para el pincel
    public static int current_brush = Color.BLACK;

    private Bitmap mBitmapStar;
    private Vector mBitmapStarDimensions;

    private Bitmap mBitmapFace;
    private Vector mBitmapFaceDimensions;

    private List<Vector> positionsStar = new ArrayList<Vector>(100);
    private List<Vector> positionsFace = new ArrayList<Vector>(100);




    public Lienzo(Context context) {
        super(context);
        init(context);
    }

    public Lienzo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Lienzo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //Iniciamos los pinceles
    private void init(Context context){
        paint_brush.setAntiAlias(true);
        paint_brush.setColor(Color.BLACK);
        paint_brush.setStyle(Paint.Style.STROKE);
        paint_brush.setStrokeCap(Paint.Cap.ROUND);
        paint_brush.setStrokeJoin(Paint.Join.ROUND);
        paint_brush.setStrokeWidth(50f);


        mBitmapStar = BitmapFactory.decodeResource(context.getResources(), R.drawable.star);
        mBitmapStarDimensions = new Vector(mBitmapStar.getWidth(), mBitmapStar.getHeight());

        mBitmapFace = BitmapFactory.decodeResource(context.getResources(), R.drawable.face);
        mBitmapFaceDimensions = new Vector(mBitmapFace.getWidth(), mBitmapFace.getHeight());

        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /*
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
                invalidate();
                return true;
            default:
                return false;
        }

    }

     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final float posX = event.getX();
        final float posY = event.getY();

        int action = event.getAction();
        switch (action) {
            //Cuando se pulsa con el dedo
            case MotionEvent.ACTION_DOWN:
                path.moveTo(posX,posY);
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:

            if(brush){
                path.lineTo(posX,posY);
                pathList.add(path);
                colorList.add(current_brush);

            }

            if(start) {

                positionsStar.add(new Vector(posX - mBitmapStarDimensions.x / 2, posY - mBitmapStarDimensions.y / 2));
            }

            if(face){
                positionsFace.add(new Vector(posX - mBitmapFaceDimensions.x / 2, posY - mBitmapFaceDimensions.y / 2));
            }




                invalidate();
        }

        return true;
    }






    //En este método colocamos lo que vamos a dibujar en el linezo.
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //pinceles circulo
        for (int i = 0; i < pathList.size(); i++) {
            paint_brush.setColor(colorList.get(i));
            canvas.drawPath(pathList.get(i), paint_brush);
            invalidate();
        }

            //pincel estrella
            for (Vector pos : positionsStar) {
                canvas.drawBitmap(mBitmapStar, pos.x, pos.y, null);
            }

        //pincel cara
        for (Vector pos : positionsFace) {
            canvas.drawBitmap(mBitmapFace, pos.x, pos.y, null);
        }


















    }

    private static class Vector {
        public Vector(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public final float x;
        public final float y;
    }
}
