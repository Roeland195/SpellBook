package com.example.spelllist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class recyclerAdapterSingleSpell extends RecyclerView.Adapter<recyclerAdapterSingleSpell.SpellViewHolder> {
    private ArrayList<String> spellItemList;
    private ArrayList<String> addonList;

    public recyclerAdapterSingleSpell(ArrayList<String> spellItems, ArrayList<String> addonList) {
        this.spellItemList = spellItems;
        this.addonList = addonList;
    }

    public class SpellViewHolder extends RecyclerView.ViewHolder {
        private TextView itemText;
        private TextView itemTextAddons;

        public SpellViewHolder(final View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.namePlace);
            itemTextAddons = itemView.findViewById(R.id.addon);
        }
    }

    @NonNull
    @Override
    public SpellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_spell_item, parent, false);
        return new SpellViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpellViewHolder holder, int position) {
        String name = spellItemList.get(position).toLowerCase();
        holder.itemText.setText(name);
        holder.itemTextAddons.setText(addonList.get(position));
    }

    @Override
    public int getItemCount() {
        return spellItemList.size();
    }
}
