package com.example.spelllist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                TODO: Get data from dataBase, When there is a Database

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        }, 3000);
    }
}
