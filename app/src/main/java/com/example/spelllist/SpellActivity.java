package com.example.spelllist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.spelllist.models.Spell;

import java.util.ArrayList;

public class SpellActivity extends AppCompatActivity implements RecyclerViewClickInterface {
    firebaseConnection firebaseConnection = com.example.spelllist.firebaseConnection.getInstance();
    ArrayList<Spell> spells = new ArrayList<>();
    private RecyclerView spellListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell);
        getSupportActionBar().hide();

        spellListView = findViewById(R.id.spellList);

        final ImageButton backToPage = findViewById(R.id.backToPrev);
        backToPage.setOnClickListener(this::backToPage);

        final Button mostUsedSpells = findViewById(R.id.mostUsedSpells);
        mostUsedSpells.setOnClickListener(this::onMostUsedSpellsClick);

        getSpellsFromDB();
    }

    private void backToPage(View view) {
        this.finish();
    }

    private void onMostUsedSpellsClick(View view){
        Intent intent = new Intent(SpellActivity.this, MostUsedSpells.class);
        startActivity(intent);
    }

    private void getSpellsFromDB(){
        firebaseConnection.getSpells(spell ->{
            spells = spell;

            recyclerAdapterSpell adapter = new recyclerAdapterSpell(spells, this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            spellListView.setLayoutManager(layoutManager);
            spellListView.setItemAnimator(new DefaultItemAnimator());
            spellListView.setAdapter(adapter);
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, SpellDescriptionActivity.class);
        intent.putExtra("spell", spells.get(position));
        startActivity(intent);
    }

    @Override
    public void onLongItemClick(int position) {

    }
}