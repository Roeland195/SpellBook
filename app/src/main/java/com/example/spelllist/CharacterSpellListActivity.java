package com.example.spelllist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CharacterSpellListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_spell_list);
        getSupportActionBar().hide();
    }
}