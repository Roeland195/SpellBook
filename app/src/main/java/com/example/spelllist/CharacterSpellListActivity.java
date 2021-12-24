package com.example.spelllist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spelllist.models.Character;
import com.example.spelllist.models.Spell;

import java.util.ArrayList;

public class CharacterSpellListActivity extends AppCompatActivity implements RecyclerViewClickInterface {
    private ArrayList<Spell> spellBook;
    private TextView spellName;
    private Character character;
    private RecyclerView spellListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_spell_list);
        getSupportActionBar().hide();

        spellName = findViewById(R.id.characterName);
        spellListView = findViewById(R.id.spellListView);
        spellBook = new ArrayList<Spell>();

        character = (Character) getIntent().getSerializableExtra("character");

        final ImageButton backToPage = findViewById(R.id.backToPrev);
        backToPage.setOnClickListener(this::backToPage);

        setCharacterDetails();
    }

    private void backToPage(View view) {
        this.finish();
    }

    private void setCharacterDetails(){
        spellName.setText(character.getName());
        spellBook = character.getSpells();

        recyclerAdapterSpell adapter = new recyclerAdapterSpell(spellBook, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        spellListView.setLayoutManager(layoutManager);
        spellListView.setItemAnimator(new DefaultItemAnimator());
        spellListView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, SpellDescriptionActivity.class);
        intent.putExtra("spell", spellBook.get(position));
        startActivity(intent);
    }

    @Override
    public void onLongItemClick(int position) {

    }
}