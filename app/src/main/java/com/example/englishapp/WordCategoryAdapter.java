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
    private final OnWordCategoryClickListener onClickListener;

    interface OnWordCategoryClickListener{
        void onWordCategoryClickListener(WordCategory state, int position);
    }

    public class WordCategoryHolder extends RecyclerView.ViewHolder {

        private TextView view;
        public WordCategoryHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.categoryName);
        }

        public TextView getView(){
            return view;
        }
    }

    public WordCategoryAdapter(List<WordCategory> categories, OnWordCategoryClickListener onClickListener) {
        this.categories = categories;
        this.onClickListener = onClickListener;
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

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                // вызываем метод слушателя, передавая ему данные
                onClickListener.onWordCategoryClickListener(wordCategory, holder.getBindingAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
