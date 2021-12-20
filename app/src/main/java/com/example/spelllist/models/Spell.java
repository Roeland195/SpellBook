package com.example.spelllist.models;

import android.content.Intent;

import java.util.ArrayList;

public class Spell {
    private ArrayList<String> components;
    private Intent level;
    private String name;
    private String book;
    private String duration;
    private String range;
    private String school;
    private String time;

    public Spell(ArrayList<String> components, Intent level, String name, String book, String duration, String range, String school, String time) {
        this.components = components;
        this.level = level;
        this.name = name;
        this.book = book;
        this.duration = duration;
        this.range = range;
        this.school = school;
        this.time = time;
    }

    public Spell() {
    }

    public ArrayList<String> getComponents() {
        return components;
    }

    public Intent getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getBook() {
        return book;
    }

    public String getDuration() {
        return duration;
    }

    public String getRange() {
        return range;
    }

    public String getSchool() {
        return school;
    }

    public String getTime() {
        return time;
    }
}
