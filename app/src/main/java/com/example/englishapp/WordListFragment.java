package com.example.englishapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WordListFragment extends Fragment {

    ArrayList<Word> words;
    WordAdapter wordAdapter;

    private RecyclerView recyclerView;
    private String wordSoundRes = "https://www.english-easy.info/talker/words/";
    private OnFragmentSendDataListener fragmentSendDataListener;

    //Fields for work with database
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private Cursor query;

    //Fields for work with firebase
    private FirebaseDatabase firebaseDB;
    private String dbLink = "https://englishapp-df661-default-rtdb.asia-southeast1.firebasedatabase.app";
    private DatabaseReference userWords;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_word_list, container, false);

        WordAdapter.OnWordClickListener wordClickListener = new WordAdapter.OnWordClickListener() {
            @Override
            public void onWordClick(Word word, int position) {
                /*Snackbar.make(getActivity().findViewById(R.id.root), word.getName(), Snackbar.LENGTH_LONG)
                        .show();*/
                /*MediaPlayer.create(getActivity(), Uri.parse("https://www.english-easy.info/talker/words/tiger.mp3"))
                        .start();*/
                MediaPlayer.create(getActivity(), Uri.parse(wordSoundRes + word.getName().toLowerCase() + ".mp3"))
                        .start();
            }
        };

        words = new ArrayList<>();
        wordAdapter = new WordAdapter(words, wordClickListener);

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

    interface OnFragmentSendDataListener {
        void onSendData(String wordId);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentSendDataListener");
        }
    }
}