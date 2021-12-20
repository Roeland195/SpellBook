package com.example.spelllist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.RelativeLayout;

public class CharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        getSupportActionBar().hide();

        final Button newCharacterBtn = findViewById(R.id.addCharacter);
        newCharacterBtn.setOnClickListener(this::newPage);
    }

    private void newPage(View view) {
        Intent intent = new Intent(this, NewCharacter.class);
        startActivity(intent);
    }
}