package com.example.spelllist;

import com.example.spelllist.models.Character;

import java.util.ArrayList;

@FunctionalInterface
public interface GetCharacters {

    // Callback => Ik bel je later terug, en voer dan deze functie uit
    // RXJS
    void getCharacters(ArrayList<Character> caracters);
}
