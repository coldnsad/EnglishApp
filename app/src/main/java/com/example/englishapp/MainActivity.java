package com.example.englishapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TopicListFragment.OnFragmentSendDataListener {

    private EditText editTopicSearch;
    private TopicListFragment topicListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTopicSearch = findViewById(R.id.editTopicSearch);
        topicListFragment = new TopicListFragment();

        replaceFragment(topicListFragment);

        editTopicSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
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

    public void openProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void filter(String text) {
        ArrayList<Topic> filtered = new ArrayList<>();
        for (Topic item: topicListFragment.topics) {
            if(item.getTitle().toLowerCase().contains(text.toLowerCase())){
                filtered.add(item);
            }
        }
        topicListFragment.topicAdapter.filterList(filtered);
    }

    @Override
    public void onSendData(String topicId) {

    }
}