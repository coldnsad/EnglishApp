package com.example.englishapp;

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
public class CategoryList extends Fragment {

    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category_list, container, false);

        // Creating RecyclerView in this fragment
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new WordCategoryAdapter(GenerateCategories()));

        return view;
    }

    private static List<WordCategory> GenerateCategories() {
        List<WordCategory> categories = new ArrayList<>();
        categories.add(new WordCategory("cat123123123123123123213124124124", "image1"));
        categories.add(new WordCategory("cat2", "image1"));
        categories.add(new WordCategory("cat3", "image1"));
        categories.add(new WordCategory("cat4", "image1"));
        categories.add(new WordCategory("cat5", "image1"));
        categories.add(new WordCategory("cat6", "image1"));
        categories.add(new WordCategory("cat7", "image1"));
        categories.add(new WordCategory("cat8", "image1"));
        categories.add(new WordCategory("cat9", "image1"));

        return categories;
    }
}