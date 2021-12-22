package com.example.spelllist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.spelllist.models.Character;
import com.example.spelllist.models.Spell;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CharacterActivity extends AppCompatActivity {
    firebaseConnection firebaseConnection = com.example.spelllist.firebaseConnection.getInstance();
    ArrayList<Character> characters = new ArrayList<>();
    private RecyclerView characterListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        getSupportActionBar().hide();
        characterListView = findViewById(R.id.characterList);

        final Button newCharacterBtn = findViewById(R.id.addCharacter);
        newCharacterBtn.setOnClickListener(this::newPage);

        getCharactersFromDB();
    }


    private void getCharactersFromDB() {
        ArrayList<String> component = new ArrayList<>();
        component.add("dirt");
        component.add("stone");
        Spell spell = new Spell(component, 2,"wish","phb","1 minut", "10 feet", "conjuration","action");
//        firebaseConnection.writeSpellToFirebase(spell);

        firebaseConnection.getCharacters(character -> {
            Log.d("tag", "getCharactersFromDB: ");
            characters = character;

            recyclerAdapter adapter = new recyclerAdapter(characters);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            characterListView.setLayoutManager(layoutManager);
            characterListView.setItemAnimator(new DefaultItemAnimator());
            characterListView.setAdapter(adapter);
        });
    }

    private void newPage(View view) {
        Intent intent = new Intent(this, NewCharacter.class);
        startActivity(intent);
    }
}