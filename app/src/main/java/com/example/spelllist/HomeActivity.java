package com.example.spelllist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class HomeActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);
            getSupportActionBar().hide();

            RelativeLayout characterBtn = findViewById(R.id.image_btn_character);
            RelativeLayout spellBtn = findViewById(R.id.image_btn_spell);

            characterBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, CharacterActivity.class);
                    startActivity(intent);
                }
            });

            spellBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, SpellActivity.class);
                    startActivity(intent);
                }
            });
        }
}