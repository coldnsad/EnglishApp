package com.example.englishapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WordListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Word> words;
    private WordAdapter wordAdapter;

    //Fields for work with database
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private Cursor query;

    private FirebaseDatabase firebaseDB;
    private String dbLink = "https://englishapp-df661-default-rtdb.asia-southeast1.firebasedatabase.app";
    private DatabaseReference userWords;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_word_list, container, false);
        words = new ArrayList<>();
        wordAdapter = new WordAdapter(words);

        firebaseDB = FirebaseDatabase.getInstance(dbLink);
        userWords = firebaseDB.getReference("User_words").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        databaseHelper = new DatabaseHelper(view.getContext());
        // Creating RecyclerView in this fragment
        recyclerView = view.findViewById(R.id.wordRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        generateWords();
        recyclerView.setAdapter(wordAdapter);
        return view;
    }

    private void generateWords() {

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                words.clear();
                for (DataSnapshot ds: snapshot.getChildren()){
                    Word word = ds.getValue(Word.class);
                    words.add(word);
                }
                wordAdapter.setWordsList(words);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };
        userWords.addValueEventListener(valueEventListener);
    }
}