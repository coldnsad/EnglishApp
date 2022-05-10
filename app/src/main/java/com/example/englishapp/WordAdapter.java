package com.example.englishapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordHolder> {


    private Random random;
    private final List<Word> words;

    public class WordHolder extends RecyclerView.ViewHolder {

        private TextView view;
        public WordHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.word_name);
        }

        public TextView getView(){
            return view;
        }
    }

    /*public WordCategoryAdapter(int seed) {
        this.random = new Random(seed);
    }*/

    public WordAdapter(List<Word> words) {
        this.words = words;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.item_word;
    }

    @NonNull
    @Override
    public WordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false);
        return new WordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordHolder holder, int position) {
        Word word = words.get(position);
        holder.getView().setText(word.getName());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }
}
