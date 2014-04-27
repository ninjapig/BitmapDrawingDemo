package com.example.maptest;

import android.graphics.*;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends ActionBarActivity {
    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imgView = (ImageView)findViewById(R.id.imageView);
        //create a mutable bitmap, takes an image resource file
//        Bitmap map = BitmapFactory.decodeResource(getResources(), R.drawable.sqare_image);
//        Bitmap map = BitmapFactory.decodeResource(getResources(), R.drawable.ens_6);
        Bitmap map = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ens_6),
                480, 674, true);
        Bitmap pin = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.map_pointer),
                40, 40, true);

        Bitmap mapMutable = map.copy(Bitmap.Config.ARGB_8888, true);
        imgView.setImageBitmap(mapMutable);
        Canvas canvas = new Canvas(mapMutable);
        canvas.drawBitmap(pin,0,0,null);
        canvas.drawBitmap(pin, 200, 500, null);


        ExecutorService execService = Executors.newCachedThreadPool();
        execService.execute(new PinDraw(this, imgView));
        execService.execute(new GetPage());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
