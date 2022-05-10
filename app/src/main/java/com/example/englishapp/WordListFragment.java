package com.example.englishapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class WordListFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Word> words;

    //Fields for work with database
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private Cursor query;
    private String word_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_word_list, container, false);

        databaseHelper = new DatabaseHelper(view.getContext());
        // Creating RecyclerView in this fragment
        recyclerView = view.findViewById(R.id.wordRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new WordAdapter(GenerateWords()));

        return view;
    }

    private List<Word> GenerateWords() {

        words = new ArrayList<>();

        db = databaseHelper.getReadableDatabase();
        query =  db.rawQuery("select * from "+ DatabaseHelper.TABLE_WORDS, null);

        while (query.moveToNext()){
            word_name = query.getString(1);
            words.add(new Word(word_name, "image1"));
        }

       /* words.add(new Word("1", "image1"));
        words.add(new Word("2", "image1"));
        words.add(new Word("3", "image1"));
        words.add(new Word("4", "image1"));
        words.add(new Word("5", "image1"));*/

        return words;
    }
}