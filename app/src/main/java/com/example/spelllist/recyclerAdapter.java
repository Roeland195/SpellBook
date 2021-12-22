package com.example.spelllist;

import android.view.LayoutInflater;
import com.example.spelllist.models.Character;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewholder> {
    private ArrayList<Character> characterList;

    public recyclerAdapter(ArrayList<Character> characterList){
        this.characterList = characterList;
    }

    public class MyViewholder extends RecyclerView.ViewHolder{
        private TextView nameTxt;

        public MyViewholder(final View view){
            super(view);
            nameTxt = view.findViewById(R.id.namePlace);
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_list_items, parent, false);
        return new MyViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewholder holder, int position) {
        String name = characterList.get(position).getName();
        holder.nameTxt.setText(name);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }
}