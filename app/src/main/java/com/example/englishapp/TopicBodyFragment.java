package com.example.englishapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class TopicBodyFragment extends Fragment {

    //Fields for work with database
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private Cursor query;
    private String word_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_topic_body, container, false);
        databaseHelper = new DatabaseHelper(view.getContext());

        TextView viewBody = view.findViewById(R.id.topicBodyTextView);
        TextView viewLesson = view.findViewById(R.id.topicLessonTextView);
        viewBody.setText(getArguments().getString("TOPIC_BODY"));
        viewLesson.setText("Lesson " + getArguments().getString("TOPIC_SERIAL_NUMBER"));

        return view;
    }

    //This Factory method need for sending some info to the fragment, also creates instance of fragment
    public static TopicBodyFragment newInstance(String topicBody, String topicSerialNumber) {

        Bundle args = new Bundle();
        args.putString("TOPIC_BODY", topicBody);
        args.putString("TOPIC_SERIAL_NUMBER", topicSerialNumber);

        TopicBodyFragment fragment = new TopicBodyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /*private List<Topic> GenerateTopics() {

        topics = new ArrayList<>();

        db = databaseHelper.getReadableDatabase();
        query =  db.rawQuery("select * from "+ DatabaseHelper.TABLE_WORDS, null);

        while (query.moveToNext()){
            word_name = query.getString(1);
            topics.add(new Word(word_name, "image1"));
        }

        //topics.add(new Topic("Построение предложений в английском языке I/We/You/They", 1));
        topics.add(new Topic("Построение предложений в английском языке I/We/You/They/1/1/1/1/1", 1));
        topics.add(new Topic("1", 2));
        topics.add(new Topic("1", 3));
        topics.add(new Topic("1", 4));
        topics.add(new Topic("1", 5));

        return topics;
    }*/
}