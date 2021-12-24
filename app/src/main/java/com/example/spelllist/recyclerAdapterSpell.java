package com.example.spelllist;

import android.view.LayoutInflater;
import com.example.spelllist.models.Spell;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapterSpell extends RecyclerView.Adapter<recyclerAdapterSpell.SpellViewHolder> {
    private ArrayList<Spell> spellList;
    private RecyclerViewClickInterface recyclerViewClickInterface;

    public recyclerAdapterSpell(ArrayList<Spell> spellList, RecyclerViewClickInterface recyclerViewClickInterface){
        this.spellList = spellList;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    public class SpellViewHolder extends RecyclerView.ViewHolder {
        private TextView nameText;

        public SpellViewHolder(final View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.namePlace);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener((View) -> {
                recyclerViewClickInterface.onLongItemClick(getAdapterPosition());
                return true;
            });
        }
    }

    @NonNull
    @Override
    public SpellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spell_list_item, parent, false);
        return new SpellViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpellViewHolder holder, int position) {
        String name = spellList.get(position).getName();
        holder.nameText.setText(name);
    }

    @Override
    public int getItemCount() {
        return spellList.size();
    }
}