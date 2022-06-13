package com.example.englishapp;

import android.content.Intent;
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
public class CategoryListFragment extends Fragment {

    ArrayList<WordCategory> categories;
    WordCategoryAdapter wordCategoryAdapter;

    private RecyclerView recyclerView;

    //Fields for work with database
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private Cursor query;
    private String category_name;
    private int category_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category_list, container, false);

        databaseHelper = new DatabaseHelper(view.getContext());
        // Creating RecyclerView in this fragment
        recyclerView = view.findViewById(R.id.categoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        WordCategoryAdapter.OnWordCategoryClickListener topicClickListener = new WordCategoryAdapter.OnWordCategoryClickListener() {
            @Override
            public void onWordCategoryClickListener(WordCategory wordCategory, int position) {

                /*Toast.makeText(view.getContext(), "Был выбран пункт " + wordCategory.getName(),
                        Toast.LENGTH_SHORT).show();*/

                Intent intent = new Intent(getContext(), WordsDialogActivity.class);
                intent.putExtra("CATEGORY_ID", wordCategory.getId());
                intent.putExtra("CATEGORY_NAME", wordCategory.getName());
                startActivity(intent);
            }
        };

        wordCategoryAdapter = new WordCategoryAdapter(generateCategories(),topicClickListener);
        recyclerView.setAdapter(wordCategoryAdapter);

        return view;
    }

    private ArrayList<WordCategory> generateCategories() {

        categories = new ArrayList<>();

        db = databaseHelper.getReadableDatabase();
        query =  db.rawQuery("select * from "+ DatabaseHelper.TABLE_CATEGORIES, null);

        while (query.moveToNext()){
            category_id = query.getInt(0);
            category_name = query.getString(1);
            categories.add(new WordCategory(category_name, "image1", category_id));
        }

        db.close();
        query.close();
        return categories;
    }
}