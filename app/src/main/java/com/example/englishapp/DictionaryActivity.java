package com.example.englishapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class DictionaryActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, WordListFragment.OnFragmentSendDataListener {

    VPAdapterDictionary adapter;
    TextWatcher textWatcherWords;
    TextWatcher textWatcherCategories;

    private EditText editWordSearch;

    private WordListFragment wordListFragment;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        editWordSearch = findViewById(R.id.editWordCategorySearch);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPagerDictionary);

        tabLayout.addTab(tabLayout.newTab().setText("Слова"));
        tabLayout.addTab(tabLayout.newTab().setText("Категории"));

        FragmentManager fm = getSupportFragmentManager();
        adapter = new VPAdapterDictionary(fm, getLifecycle());
        viewPager2.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(this);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
                if(position == 0){
                    editWordSearch.setHint("Поиск по словам");
                    setWordsSearchListener();
                }else{
                    editWordSearch.setHint("Поиск по категориям");
                    setCategoriesSearchListener();
                }
            }
        });



        /*editWordSearch.addTextChangedListener(new TextWatcher() {
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
        });*/
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager2.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void openMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void onSendData(String wordId) {
        /*TopicBodyFragment fragment = new TopicBodyFragment(topicId);
        replaceFragment(fragment);*/
    }

    private void filterWords(String text){
        ArrayList<Word> filtered = new ArrayList<>();

        for (Word item: adapter.wordListFragment.words) {
            if (item.getName().toLowerCase().contains(text.toLowerCase()) ||
                    item.getTranslation().toLowerCase().contains(text.toLowerCase())){
                filtered.add(item);
            }
        }
        adapter.wordListFragment.wordAdapter.filterList(filtered);
    }

    private void filterCategories(String text){
        ArrayList<WordCategory> filtered = new ArrayList<>();

        for (WordCategory item: adapter.categoryListFragment.categories) {
            if (item.getName().toLowerCase().contains(text.toLowerCase()) ||
                    item.getName().toLowerCase().contains(text.toLowerCase())){
                filtered.add(item);
            }
        }
        adapter.categoryListFragment.wordCategoryAdapter.filterList(filtered);
    }

    public void setWordsSearchListener(){
        textWatcherWords = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterWords(editable.toString());
            }
        };
        editWordSearch.addTextChangedListener(textWatcherWords);
    }

    public void setCategoriesSearchListener(){
        textWatcherCategories = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterCategories(editable.toString());
            }
        };
        editWordSearch.addTextChangedListener(textWatcherCategories);
    }
}
