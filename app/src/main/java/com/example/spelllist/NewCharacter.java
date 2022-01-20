package com.example.spelllist;

import androidx.appcompat.app.AppCompatActivity;
import com.example.spelllist.models.Character;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class NewCharacter extends AppCompatActivity {
    firebaseConnection firebaseConnection = com.example.spelllist.firebaseConnection.getInstance();
    String image;
    Button imageSelector;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_character);
        getSupportActionBar().hide();

        imageView2 = findViewById(R.id.imageView2);
        imageSelector = findViewById(R.id.imageSelector);
        imageSelector.setOnClickListener(this::imageChooser);

        final Button createCharacter = findViewById(R.id.createCharacter);
        createCharacter.setOnClickListener(this::addCharacter);

        final ImageButton backToPage = findViewById(R.id.backToPrev);
        backToPage.setOnClickListener(this::backToPage);
    }

    private void imageChooser(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent,"Select Picture"), 200);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 200
            && resultCode == RESULT_OK
            && data.getData() != null) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    image = selectedImageUri.toString();
                    Uri imgUri=Uri.parse(image);
                    imageView2.setImageURI(imgUri);
                }
            }
        }
    }

    private void backToPage(View view) {
        this.finish();
    }

    private void addCharacter(View view) {
        firebaseConnection.writeCharacterToFirebase(getCharacterInformation());
        this.finish();
    }

    private Character getCharacterInformation() {
        String characterName = ((EditText) findViewById(R.id.CharacterName)).getText().toString();
        Character character = new Character(characterName, image);
        return character;
    }
}