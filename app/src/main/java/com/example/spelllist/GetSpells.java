package com.example.spelllist;

import com.example.spelllist.models.Spell;

import java.util.ArrayList;

@FunctionalInterface
public interface GetSpells {

    // Callback => Ik bel je later terug, en voer dan deze functie uit
    // RXJS
    void getSpells(ArrayList<Spell> caracters);
}
