package com.example.spelllist.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Character implements Serializable {
    private String id;
    private String name;
    private String imgPath;
    private ArrayList<Spell> spells;

    public Character(String name, String imgPath) {
        this.name = name;
        this.imgPath = imgPath;
    }

    public Character(String id, String name, String imgPath) {
        this.name = name;
        this.imgPath = imgPath;
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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
