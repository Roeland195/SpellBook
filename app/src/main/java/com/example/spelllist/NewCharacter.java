package com.example.spelllist;

import androidx.appcompat.app.AppCompatActivity;
import com.example.spelllist.models.Character;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class NewCharacter extends AppCompatActivity {
    firebaseConnection firebaseConnection = com.example.spelllist.firebaseConnection.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_character);
        getSupportActionBar().hide();

        final Button createCharacter = findViewById(R.id.createCharacter);
        createCharacter.setOnClickListener(this::addCharacter);

        final ImageButton backToPage = findViewById(R.id.backToPrev);
        backToPage.setOnClickListener(this::backToPage);
    }

    private void backToPage(View view) {
        this.finish();
    }

    private void addCharacter(View view) {
        firebaseConnection.writeCharacterToFirebase(getCharacterInformation());
    }

    private Character getCharacterInformation() {
        String characterName = ((EditText) findViewById(R.id.CharacterName)).getText().toString();
        String characterImage = ((EditText) findViewById(R.id.CharacterImage)).getText().toString();

        Character character = new Character(characterName, characterImage);
        return character;
    }
}