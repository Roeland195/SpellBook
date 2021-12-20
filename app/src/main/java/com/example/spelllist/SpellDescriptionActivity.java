package com.example.spelllist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SpellDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_description);
        getSupportActionBar().hide();
    }
}