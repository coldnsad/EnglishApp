package com.example.englishapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class WordCategoryAdapter extends RecyclerView.Adapter<WordCategoryAdapter.WordCategoryHolder> {


    private Random random;
    private final List<WordCategory> categories;

    public class WordCategoryHolder extends RecyclerView.ViewHolder {

        private TextView view;
        public WordCategoryHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.category_name);
        }

        public TextView getView(){
            return view;
        }
    }

    /*public WordCategoryAdapter(int seed) {
        this.random = new Random(seed);
    }*/

    public WordCategoryAdapter(List<WordCategory> categories) {
        this.categories = categories;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.item_category;
    }

    @NonNull
    @Override
    public WordCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new WordCategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordCategoryHolder holder, int position) {
        WordCategory wordCategory = categories.get(position);
        holder.getView().setText(wordCategory.getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
