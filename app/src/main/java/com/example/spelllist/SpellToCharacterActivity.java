package com.example.spelllist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.spelllist.models.Character;
import com.example.spelllist.models.Spell;

import java.util.ArrayList;

public class SpellToCharacterActivity extends AppCompatActivity implements RecyclerViewClickInterface {
    firebaseConnection firebaseConnection = com.example.spelllist.firebaseConnection.getInstance();
    private Spell spell;
    ArrayList<Character> characters = new ArrayList<>();
    private RecyclerView characterListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_to_character);
        getSupportActionBar().hide();

        characterListView = findViewById(R.id.characterList);
        spell = (Spell) getIntent().getSerializableExtra("spell");

        final ImageButton backToPage = findViewById(R.id.backToPrev);
        backToPage.setOnClickListener(this::backToPage);

        getCharactersFromDB();
    }

    private void backToPage(View view) {
        this.finish();
    }


    private void getCharactersFromDB() {
        firebaseConnection.getCharacters(character -> {
            characters = character;

            recyclerAdapter adapter = new recyclerAdapter(characters, this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            characterListView.setLayoutManager(layoutManager);
            characterListView.setItemAnimator(new DefaultItemAnimator());
            characterListView.setAdapter(adapter);
        });
    }

    @Override
    public void onItemClick(int position) {
        firebaseConnection.writeSpellToCharacter(spell, characters.get(position).getId());
        Toast.makeText(this, spell.getName() + " has been added to: " + characters.get(position).getName(), Toast.LENGTH_SHORT).show();
        this.finish();
    }

    @Override
    public void onLongItemClick(int position) {
    }
}