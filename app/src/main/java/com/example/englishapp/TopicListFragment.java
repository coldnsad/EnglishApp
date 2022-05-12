package com.example.englishapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class TopicListFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Topic> topics;

    //Fields for work with database
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private Cursor query;
    private String word_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_topic_list, container, false);

        databaseHelper = new DatabaseHelper(view.getContext());
        // Creating RecyclerView in this fragment
        recyclerView = view.findViewById(R.id.topicRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.setAdapter(new TopicAdapter(GenerateTopics()));

        return view;
    }

    private List<Topic> GenerateTopics() {

        topics = new ArrayList<>();

        /*db = databaseHelper.getReadableDatabase();
        query =  db.rawQuery("select * from "+ DatabaseHelper.TABLE_WORDS, null);

        while (query.moveToNext()){
            word_name = query.getString(1);
            topics.add(new Word(word_name, "image1"));
        }*/

        topics.add(new Topic("1", 1));
        topics.add(new Topic("1", 1));
        topics.add(new Topic("1", 1));
        topics.add(new Topic("1", 1));
        topics.add(new Topic("1", 1));

        return topics;
    }
}