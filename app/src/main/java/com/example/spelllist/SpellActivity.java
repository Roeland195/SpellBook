package com.example.spelllist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.spelllist.models.Spell;

import java.util.ArrayList;

public class SpellActivity extends AppCompatActivity {
    firebaseConnection firebaseConnection = com.example.spelllist.firebaseConnection.getInstance();
    ArrayList<Spell> spells = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell);
        getSupportActionBar().hide();

        getSpellsFromDB();
    }

    private void getSpellsFromDB(){
        firebaseConnection.getSpells(spell ->{
            System.out.println("DO WE GET HERE?");
            spells = spell;

            for (int i=0; i<spells.size();i++){
                System.out.println(spells.get(i).getName());
            }
        });
    }
}