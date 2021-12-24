package com.example.spelllist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.spelllist.models.Spell;

import java.util.ArrayList;

public class SpellDescriptionActivity extends AppCompatActivity {
    private RecyclerView spellListView;
    private ArrayList<String> spellItems;
    private ArrayList<String> addonList;
    private TextView spellDesc;
    private TextView spellName;
    private Spell spell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_description);
        getSupportActionBar().hide();

        spellDesc = findViewById(R.id.spellDescription);
        spellDesc.setMovementMethod(new ScrollingMovementMethod());
        spellName = findViewById(R.id.spellName);

        spellListView = findViewById(R.id.single_spell_list_item);
        spellItems = new ArrayList<>();
        addonList = new ArrayList<>();

        final ImageButton backToPage = findViewById(R.id.backToPrev);
        backToPage.setOnClickListener(this::backToPage);

        final Button addToCharacter = findViewById(R.id.addToCharacter);
        addToCharacter.setOnClickListener(this::addToCharacter);

        spell = (Spell) getIntent().getSerializableExtra("spell");
        setSpellOnPage(spell);
    }

    private void backToPage(View view) {
        this.finish();
    }

    private void addToCharacter(View view) {
        Intent intent = new Intent(this, SpellToCharacterActivity.class);
        intent.putExtra("spell", spell);
        startActivity(intent);
    }

    private void setSpellOnPage(Spell spell){
        spellName.setText(spell.getName());
        spellDesc.setText(spell.getDesc());

        spellItems.add(spell.getLevel());
        spellItems.add(spell.getSchool());
        spellItems.add(spell.getTime());
        spellItems.add(spell.getRange());
        spellItems.add(spell.getComponents());
        spellItems.add(spell.getDuration());
        spellItems.add(spell.getdClass());
        spellItems.add(spell.getConcentration());
        spellItems.add(spell.getMaterial());
        spellItems.add(spell.getRitual());

        addonList.add("Level:");
        addonList.add("School:");
        addonList.add("Casting time:");
        addonList.add("Range:");
        addonList.add("Components:");
        addonList.add("Duration:");
        addonList.add("Classes:");
        addonList.add("Concentration:");
        addonList.add("Material:");
        addonList.add("Ritual:");

        recyclerAdapterSingleSpell adapter = new recyclerAdapterSingleSpell(spellItems, addonList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        spellListView.setLayoutManager(layoutManager);
        spellListView.setItemAnimator(new DefaultItemAnimator());
        spellListView.setAdapter(adapter);


    }
}