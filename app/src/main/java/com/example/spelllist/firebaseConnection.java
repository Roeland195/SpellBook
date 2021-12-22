package com.example.spelllist;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.spelllist.models.Character;
import com.example.spelllist.models.Spell;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


class firebaseConnection {
    static firebaseConnection firebaseConnection;
    private ArrayList<Spell> spells = new ArrayList<Spell>();

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

//    GET CHARACTERS FROM FB
    public void getCharacters(GetCharacters get){
        ArrayList<Character> characters = new ArrayList<Character>();
        dbRefCharacter.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                characters.clear();
                String name = "";
                String imgPath = "";
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    for(DataSnapshot snap : dataSnapshot.getChildren()){
                        if(snap.getKey().equals("name")){
                            name = snap.getValue().toString();
                        }else{
                            imgPath = snap.getValue().toString();
                        }
                    }
                    Character character = new Character(dataSnapshot.getKey() ,name ,imgPath);
                    System.out.println(character.getId() + " : " + character.getName() +" : "+ character.getImgPath());
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

//    SET CHARACTER IN FB
    public void writeCharacterToFirebase(Character character){
        String id =this.databaseReference.push().getKey();
        character.setId(id);

        databaseReference.child("Character").child(id).setValue(character);
    }

//    WRITE SPELLS TO DATABASE __USE WITH JSON--
    public void writeSpellToFirebase(Spell spell){
        String id =this.dbRefSpells.push().getKey();
        spell.setId(id);

        dbRefSpells.child(spell.getName()).setValue(spell);
    }
}
