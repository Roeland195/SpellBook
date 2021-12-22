package com.example.spelllist.models;

import android.content.Intent;

import java.util.ArrayList;

public class Spell {
    private String components;
    private String level;
    private String name;
    private String dClass;
    private String duration;
    private String range;
    private String school;
    private String time;
    private String desc;
    private String concentration;
    private String ritual;
    private String material;


    public Spell(String components, String level,       String name,
                 String dndClass,   String duration,    String range,
                 String school,     String time,        String desc,
                 String con,        String ritual,      String material) {
        this.components = components;
        this.level = level;
        this.name = name;
        this.dClass = dndClass;
        this.duration = duration;
        this.range = range;
        this.school = school;
        this.time = time;
        this.desc = desc;
        this.concentration = con;
        this.ritual = ritual;
        this.material = material;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getdClass() {
        return dClass;
    }

    public void setdClass(String dClass) {
        this.dClass = dClass;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }

    public String getRitual() {
        return ritual;
    }

    public void setRitual(String ritual) {
        this.ritual = ritual;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
