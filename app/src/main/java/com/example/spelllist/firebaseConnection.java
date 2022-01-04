package com.example.spelllist;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.spelllist.models.Character;
import com.example.spelllist.models.Spell;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;


class firebaseConnection {
    static firebaseConnection firebaseConnection;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance("https://iwder-s1128038-default-rtdb.europe-west1.firebasedatabase.app/");
    private final DatabaseReference databaseReference = database.getReference();
    private final DatabaseReference dbRefCharacter = database.getReference("Character");
    private final DatabaseReference dbRefSpells = database.getReference("Spell");

    public static firebaseConnection getInstance() {
        if (firebaseConnection == null) {
            firebaseConnection = new firebaseConnection();
        }
        return firebaseConnection;
    }

//    GET FROM FB

//    get characters
    public void getCharacters(GetCharacters get){
        ArrayList<Character> characters = new ArrayList<Character>();
        dbRefCharacter.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                characters.clear();
                String name = "";
                String image = "";
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ArrayList<Spell> spellBook = new ArrayList<>();
                    for(DataSnapshot snap : dataSnapshot.getChildren()){
                        if(snap.getKey().equals("name")){
                            name = snap.getValue().toString();
                        }if(snap.getKey().equals("imgPath")){
                            image = snap.getValue().toString();
                        }if(snap.getKey().equals("Spells")){
                            for(DataSnapshot snapshot1: snap.getChildren()){
                                spellBook.add(getModelOfSpell(snapshot1));
                            }
                        }
                    }
                    Character character = new Character(dataSnapshot.getKey() ,name ,image, spellBook);
                    characters.add(character);
                }
                get.getCharacters(characters);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("fail","Failed to read value.", error.toException());
            }
        });
    }

//  Get Spells
    public void getSpells(GetSpells get){
        ArrayList<Spell> spells = new ArrayList<Spell>();
        dbRefSpells.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                spells.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        spells.add(getModelOfSpell(dataSnapshot));
                }
                get.getSpells(spells);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("fail","Failed to read value.", error.toException());
            }
        });
    }


//      PUT IN FIREBASE

//    set characters
    public void writeCharacterToFirebase(Character character){
        String id =this.databaseReference.push().getKey();
        character.setId(id);

        databaseReference.child("Character").child(id).setValue(character);
    }

//    WRITE SPELLS TO DATABASE __USE WITH JSON--
    public void writeSpellToFirebase(Spell spell){
        dbRefSpells.child(spell.getName()).setValue(spell);
    }

    public void writeSpellToCharacter(Spell spell, String id) {
        dbRefCharacter.child(id).child("Spells").child(spell.getName()).setValue(spell);
    }

    public Spell getModelOfSpell(DataSnapshot dataSnapshot){
        Spell spell;
        String name = "";
        String component = "";
        String level = "";
        String dndClass ="";
        String duration ="";
        String range ="";
        String school ="";
        String con ="";
        String time ="";
        String desc ="";
        String ritual ="";
        String material ="";
        for(DataSnapshot snap: dataSnapshot.getChildren()){
            switch (snap.getKey()){
                case "components":
                    component = snap.getValue().toString();
                    break;
                case "concentration":
                    con = snap.getValue().toString();
                    break;
                case "dClass":
                    dndClass = snap.getValue().toString();
                    break;
                case "desc":
                    String snapDesc = snap.getValue().toString();
                    snapDesc = snapDesc.replace("<p>", "");
                    snapDesc = snapDesc.replace("</p>","");
                    snapDesc = snapDesc.replace("<b>" , "");
                    snapDesc = snapDesc.replace("</b>","");
                    System.out.println("THIS IS THE NEW DESC: "+snapDesc);
                    desc = snapDesc;
                    break;
                case "duration":
                    duration = snap.getValue().toString();
                    break;
                case "level":
                    level = snap.getValue().toString();
                    break;
                case "material":
                    material = snap.getValue().toString();
                    break;
                case "name":
                    name = snap.getValue().toString();
                    break;
                case "range":
                    range = snap.getValue().toString();
                    break;
                case "ritual":
                    ritual = snap.getValue().toString();
                    break;
                case "school":
                    school = snap.getValue().toString();
                    break;
                case "time":
                    time = snap.getValue().toString();
                    break;

            }
        }

        spell = new Spell(
                component,  level,      name,
                dndClass,   duration,   range,
                school,     time,       desc,
                con,        ritual,     material
        );
        return spell;
    }

    public void deleteSpellFromCharacter(String id, String spell){
        dbRefCharacter.child(id).child("Spells").child(spell).removeValue();
    }
}
