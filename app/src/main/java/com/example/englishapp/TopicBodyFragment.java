package com.example.englishapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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
        TextView viewLesson = view.findViewById(R.id.topicTitleBodyTextView);
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
}