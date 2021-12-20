package com.example.spelllist;

import com.example.spelllist.models.Character;
import com.example.spelllist.models.Spell;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;


class firebaseConnection {
    static firebaseConnection firebaseConnection;
    private ArrayList<Character> characters = new ArrayList<Character>();
    private ArrayList<Spell> spells = new ArrayList<Spell>();

    private final FirebaseDatabase database = FirebaseDatabase.getInstance("https://iwder-s1128038-default-rtdb.europe-west1.firebasedatabase.app/");
    private final DatabaseReference databaseReference = database.getReference("server/saving-data/fireblog");

    public static firebaseConnection getInstance() {
        if (firebaseConnection == null) {
            firebaseConnection = new firebaseConnection();
        }
        return firebaseConnection;
    }

}
