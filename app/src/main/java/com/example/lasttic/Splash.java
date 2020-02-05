package com.example.lasttic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class Splash extends AppCompatActivity {
private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView= findViewById(R.id.splashId);

        Thread thread = new Thread(){

            @Override
            public void run() {


                try{
                    sleep(6500);

                    Intent intent= new Intent(Splash.this,MainActivity.class);
                    startActivity(intent);

                    //destroying Splash activity
                    finish();

                }


                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                super.run();
            }
        };

        thread.start();
    }
    // Share



}

