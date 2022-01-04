package com.example.spelllist.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Character implements Serializable {
    private String id;
    private String name;
    private String image;
    private ArrayList<Spell> spells;

    public Character(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Character(String id, String name, String image, ArrayList<Spell> spellBook){
        this.name = name;
        this.image = image;
        this.id = id;
        this.spells = spellBook;
    }

    public Character(String id, String name, String image) {
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public Character(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){ return id;}

    public void setSpells(ArrayList<Spell> spells) {
        this.spells = spells;
    }


}
