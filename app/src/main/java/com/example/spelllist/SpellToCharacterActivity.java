package com.example.spelllist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SpellToCharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_to_character);
        getSupportActionBar().hide();
    }
}