package com.example.englishapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordHolder> {

    private List<Word> words;
    private final WordAdapter.OnWordClickListener onClickListener;

    interface OnWordClickListener{
        void onWordClick(Word state, int position);
    }
    public class WordHolder extends RecyclerView.ViewHolder {

        private TextView viewWordName;
        private TextView viewWordTranslation;
        private TextView viewWordTranscription;
        public WordHolder(@NonNull View itemView) {
            super(itemView);
            viewWordName = itemView.findViewById(R.id.word_name);
            viewWordTranslation = itemView.findViewById(R.id.word_translation);
            viewWordTranscription = itemView.findViewById(R.id.word_transcription);
        }

        public TextView getViewWordName(){
            return viewWordName;
        }

        public TextView getViewWordTranslation() {
            return viewWordTranslation;
        }

        public TextView getViewWordTranscription() {
            return viewWordTranscription;
        }
    }

    public WordAdapter(List<Word> words, WordAdapter.OnWordClickListener onClickListener) {
        this.words = words;
        this.onClickListener = onClickListener;
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
        holder.getViewWordName().setText(word.getName());
        holder.getViewWordTranslation().setText(word.getTranslation());
        holder.getViewWordTranscription().setText(word.getTranscription());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                // вызываем метод слушателя, передавая ему данные
                onClickListener.onWordClick(word, holder.getBindingAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public void setWordsList(ArrayList<Word> words) {
        this.words = words;
        notifyDataSetChanged();
    }
}
