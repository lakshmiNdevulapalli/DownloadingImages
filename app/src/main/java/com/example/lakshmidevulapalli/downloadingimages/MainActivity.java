package com.example.lakshmidevulapalli.downloadingimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView downloadedImage;

    public void clickMe(View view){

        //https://vignette.wikia.nocookie.net/p__/images/9/9e/Edward_Brock_%28Earth-30847%29_and_Venom_%28Klyntar%29_%28Earth-30847%29_from_Marvel_vs._Capcom_Infinity_0001.png/revision/latest?cb=20180709050234&path-prefix=protagonist
        ImageDownload task = new ImageDownload();
        Bitmap myImage;
        try {
            myImage = task.execute("https://vignette.wikia.nocookie.net/p__/images/9/9e/Edward_Brock_%28Earth-30847%29_and_Venom_%28Klyntar%29_%28Earth-30847%29_from_Marvel_vs._Capcom_Infinity_0001.png/revision/latest?cb=20180709050234&path-prefix=protagonist").get();
            downloadedImage.setImageBitmap(myImage);

        } catch (Exception e) {

            e.printStackTrace();
        }
        Log.i("Button","clicked");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadedImage = findViewById(R.id.imageView);
    }

    public class ImageDownload extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;

            } catch (MalformedURLException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }

            return null;
        }
    }
}
