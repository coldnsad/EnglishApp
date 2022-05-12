package com.example.englishapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Topic> topics;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        // Creating RecyclerView
        recyclerView = findViewById(R.id.topicRecyclerView);

        TopicAdapter.OnTopicClickListener topicClickListener = new TopicAdapter.OnTopicClickListener() {
            @Override
            public void onTopicClick(Topic topic, int position) {

                Toast.makeText(getApplicationContext(), "Был выбран пункт " + topic.getSerialNumber(),
                        Toast.LENGTH_SHORT).show();
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TopicAdapter(GenerateTopics(), topicClickListener));
    }

    private List<Topic> GenerateTopics() {

        topics = new ArrayList<>();

        /*db = databaseHelper.getReadableDatabase();
        query =  db.rawQuery("select * from "+ DatabaseHelper.TABLE_WORDS, null);

        while (query.moveToNext()){
            word_name = query.getString(1);
            topics.add(new Word(word_name, "image1"));
        }*/

        //topics.add(new Topic("Построение предложений в английском языке I/We/You/They", 1));
        topics.add(new Topic("Построение предложений в английском языке I/We/You/They/1/1/1/1/1", 1));
        topics.add(new Topic("1", 2));
        topics.add(new Topic("1", 3));
        topics.add(new Topic("1", 4));
        topics.add(new Topic("1", 5));

        return topics;
    }

    public void openDictionary(View view) {
        Intent intent = new Intent(this, DictionaryActivity.class);
        startActivity(intent);
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

}