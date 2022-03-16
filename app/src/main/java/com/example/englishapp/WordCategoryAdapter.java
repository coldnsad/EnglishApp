package com.example.englishapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordCategoryAdapter extends RecyclerView.Adapter<WordCategoryAdapter.WordCategoryHolder> {

    private final List<WordCategory> categories;

    public WordCategoryAdapter(List<WordCategory> categories){
        this.categories = categories;
    }

    static class WordCategoryHolder extends RecyclerView.ViewHolder{

        public final TextView name;

        public WordCategoryHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
        }
    }

    @NonNull
    @Override
    public WordCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_category, parent, false);
        return new WordCategoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordCategoryHolder holder, int position) {
        WordCategory category = categories.get(position);
        holder.name.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
