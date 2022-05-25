package com.example.englishapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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

public class MainActivity extends AppCompatActivity implements TopicListFragment.OnFragmentSendDataListener {

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

        replaceFragment(new TopicListFragment());
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayoutTopics, fragment);
        ft.commit();
    }

    public void openDictionary(View view) {
        Intent intent = new Intent(this, DictionaryActivity.class);
        //Intent intent = new Intent(this, WordsDialogActivity.class);
        startActivity(intent);
    }

    public void openSettings(View view) {
        //Intent intent = new Intent(this, SettingsActivity.class);
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSendData(String topicId) {
        /*TopicBodyFragment fragment = new TopicBodyFragment(topicId);
        replaceFragment(fragment);*/
    }
}