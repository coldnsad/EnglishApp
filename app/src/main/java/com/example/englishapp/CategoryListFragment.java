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
public class CategoryListFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<WordCategory> categories;

    //Fields for work with database
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private Cursor query;
    private String category_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category_list, container, false);

        databaseHelper = new DatabaseHelper(view.getContext());
        // Creating RecyclerView in this fragment
        recyclerView = view.findViewById(R.id.categoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new WordCategoryAdapter(GenerateCategories()));

        return view;
    }

    private List<WordCategory> GenerateCategories() {

        categories = new ArrayList<>();

        db = databaseHelper.getReadableDatabase();
        query =  db.rawQuery("select * from "+ DatabaseHelper.TABLE_CATEGORIES, null);

        while (query.moveToNext()){
            category_name = query.getString(1);
            categories.add(new WordCategory(category_name, "image1"));
        }

        return categories;
    }
}