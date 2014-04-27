package com.example.maptest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.widget.ImageView;

public class PinDraw implements Runnable{
    private final static String TAG = "PIN_DRAW";
    private final Activity mActivity;
    private final ImageView imgView;

    public PinDraw(Activity mActivity, ImageView imgView) {
        this.mActivity = mActivity;
        this.imgView = imgView;
    }

    @Override
    public void run() {
        Log.d(TAG, "Drawing pin from new thread");

//        Bitmap map = BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.large_square);
        Bitmap map = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.ens_6),
                480, 674, true);
        final Bitmap pin = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.map_pointer),
                40, 40, true);
        int xF = 40, yF=40;

        for(int i=0;i<4;i++){
            final Bitmap mapMutable = map.copy(Bitmap.Config.ARGB_8888, true);
            final int x =xF,y=yF;
            Log.d(TAG, mapMutable.toString()+" "+xF+" "+yF);
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imgView.setImageBitmap(mapMutable);
                }
            });

            Canvas canvas = new Canvas(mapMutable);
            canvas.drawBitmap(pin,x,y,null);
            canvas.drawBitmap(pin,x+25,y+25,null);
            Log.d(TAG, "Checkpoint:"+x+" "+y);

            xF+=100;
            yF+=100;
            try {
                Thread.sleep(2*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
