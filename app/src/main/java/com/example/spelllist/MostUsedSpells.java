package com.example.spelllist;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.spelllist.models.Character;
import com.example.spelllist.models.Spell;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class MostUsedSpells extends AppCompatActivity {
    firebaseConnection firebaseConnection = com.example.spelllist.firebaseConnection.getInstance();
    ArrayList<Character> characters = new ArrayList<>();
    ArrayList<spellCount> usedSpells = new ArrayList<>();
    ArrayList<String> SPELLS = new ArrayList<>();

    public class spellCount{
        private String spellName;
        private int count;

        public spellCount(String name,int count){
            this.spellName = name;
            this.count = count;
        }

        public void setCount(){this.count = count+1;}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_used_spells);
        getSupportActionBar().hide();

        final ImageButton backToPage = findViewById(R.id.backToPrev);
        backToPage.setOnClickListener(this::backToPage);

        this.createListOfUsedSpellsFromDB();

    }

    private void createListOfUsedSpellsFromDB() {
        firebaseConnection.getCharacters(character -> {
            characters = character;
            characters.forEach(singleCharacter ->{
                singleCharacter.getSpells().forEach(spell -> {
                    if(usedSpells.size() == 0){
                        usedSpells.add(new spellCount(spell.getName(), 1));
                    }else{
                        AtomicBoolean spellExist = new AtomicBoolean(false);
                        for(int i = 0; i< usedSpells.size(); i++){
                            if(usedSpells.get(i).spellName.equals(spell.getName())){
                                usedSpells.get(i).setCount();
                                spellExist.set(true);
                            }
                        }
                        if(!spellExist.get()){
                            usedSpells.add(new spellCount(spell.getName(), 1));
                        }
                        spellExist.set(false);
                    }
                });
            });
            this.createBarchartOfSpells();
        });
    }

    private void createBarchartOfSpells(){
        BarChart UsedSpells = (BarChart) findViewById(R.id.spellChart);
        BarData data = new BarData();
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for(int i = 0; i< usedSpells.size(); i++){
            barEntries.add(new BarEntry((float) i, usedSpells.get(i).count));
        }


        BarDataSet set = new BarDataSet(barEntries, "Spell");
        data.addDataSet(set);

        XAxis xAxis= UsedSpells.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                usedSpells.forEach(spellCount -> {
                   SPELLS.add(spellCount.spellName);
                });
                return SPELLS.get((int) value);
            }
        });

        UsedSpells.setData(data);
        UsedSpells.invalidate();
        set.setValueTextColor(Color.GREEN);
        set.setValueTextSize(16f);
        final Description description = new Description();
        description.setText("Most used Spells");
        UsedSpells.setDescription(description);
        UsedSpells.animateXY(2000, 2000);
    }

    private void backToPage(View view) {
        this.finish();
    }
}