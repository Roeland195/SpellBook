package com.example.spelllist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spelllist.models.Character;
import com.example.spelllist.models.Spell;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CharacterActivity extends AppCompatActivity {
    firebaseConnection firebaseConnection = com.example.spelllist.firebaseConnection.getInstance();
    ArrayList<Character> characters = new ArrayList<>();
    private RecyclerView characterListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_character);
        characterListView = findViewById(R.id.characterList);

        final Button newCharacterBtn = findViewById(R.id.addCharacter);
        newCharacterBtn.setOnClickListener(this::newPage);

        getCharactersFromDB();
        System.out.println("____________________________________________________________________________________________________");

    }


    //--------------------------------------------------
    private void getCharactersFromDB() {
        firebaseConnection.getCharacters(character -> {
            characters = character;

            recyclerAdapter adapter = new recyclerAdapter(characters);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            characterListView.setLayoutManager(layoutManager);
            characterListView.setItemAnimator(new DefaultItemAnimator());
            characterListView.setAdapter(adapter);
        });
    }

    private void newPage(View view) {
        Intent intent = new Intent(this, NewCharacter.class);
        startActivity(intent);
    }


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

            System.out.println(desc + con+ritual+material);

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