package com.example.spelllist;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spelllist.models.Character;
import com.example.spelllist.models.Spell;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class CharacterActivity extends AppCompatActivity implements RecyclerViewClickInterface{
    firebaseConnection firebaseConnection = com.example.spelllist.firebaseConnection.getInstance();
    ArrayList<Character> characters = new ArrayList<>();
    private RecyclerView characterListView;
    private TextView characterName;
    private ImageView characterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        getSupportActionBar().hide();

        characterImage = findViewById(R.id.characterImage);
        characterName = findViewById(R.id.characterName);
        characterListView = findViewById(R.id.characterList);

        final Button newCharacterBtn = findViewById(R.id.addCharacter);
        newCharacterBtn.setOnClickListener(this::newPage);

        final ImageButton backToPage = findViewById(R.id.backToPrev);
        backToPage.setOnClickListener(this::backToPage);

        getCharactersFromDB();

    }

    private void setRandomCharacter(){
        int min = 0;
        int max = characters.size();
        int random = new Random().nextInt(max-min);
        characterName.setText(characters.get(random).getName());
        try{
        Uri imgUri = Uri.parse(characters.get(random).getImage());
        characterImage.setImageURI(imgUri);
        }catch (Exception e){
            characterImage.setImageResource(R.drawable.spellbooktwo);
        }

    }

    private void backToPage(View view) {
        this.finish();
    }


    //--------------------------------------------------
    private void getCharactersFromDB() {
        firebaseConnection.getCharacters(character -> {
            characters = character;

            recyclerAdapter adapter = new recyclerAdapter(characters, this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            characterListView.setLayoutManager(layoutManager);
            characterListView.setItemAnimator(new DefaultItemAnimator());
            characterListView.setAdapter(adapter);

            if(characters.size()>0){
                setRandomCharacter();
            }
        });
    }

    private void newPage(View view) {
        Intent intent = new Intent(this, NewCharacter.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, CharacterSpellListActivity.class);
        intent.putExtra("character", characters.get(position));
        startActivity(intent);

    }


    @Override
    public void onLongItemClick(int position) {
        Snackbar snackbar = Snackbar
                .make(this.findViewById(android.R.id.content), "Are you sure?", Snackbar.LENGTH_LONG)
                .setAction("Remove", new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        firebaseConnection.deleteCharacter(characters.get(position).getId());
                        characters.remove(position);
                    }
                });
        snackbar.show();
    }

//CODE FOR ADDING SPELLS
//--------------------------------------------------------------------------------------------------

    private void read(View view) {
        try {
            readWizardJson();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void readWizardJson() throws JSONException {
        String material = "";
        JSONObject jsonObject = new JSONObject(JsonDataFromAsset());
        JSONArray jsonArray = jsonObject.getJSONArray("spells");

        for (int i=0;i<jsonArray.length();i++){
            JSONObject spell=jsonArray.getJSONObject(i);
            String component = spell.getString("components");
            String level = spell.getString("level");
            String name = spell.getString("name");
            String dndClass = spell.getString("class");
            String duration = spell.getString("duration");
            String range = spell.getString("range");
            String school = spell.getString("school");
            String time = spell.getString("casting_time");
            String desc = spell.getString("desc");
            String con = spell.getString("concentration");
            String ritual = spell.getString("ritual");
            if(spell.has("material")){
                material = spell.getString("material");
            }else{material = "";}


            Spell newspell = new Spell(
                    component,  level,      name,
                    dndClass,   duration,   range,
                    school,     time,       desc,
                    con,        ritual,     material
            );

            firebaseConnection.writeSpellToFirebase(newspell);
        }
    }

    private String JsonDataFromAsset() {
        String json=null;
        try {
            InputStream inputStream=getAssets().open("wizard.json");
            int sizeOfFile= inputStream.available();
            byte[] bufferData=new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json=new String(bufferData, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return json;
    }

}