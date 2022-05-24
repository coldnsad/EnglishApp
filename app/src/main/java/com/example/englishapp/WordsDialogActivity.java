package com.example.englishapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class WordsDialogActivity extends AppCompatActivity {

    List<Word> words;
    int counter;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor query;
    ContentValues contentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_dialog);
        databaseHelper = new DatabaseHelper(this);

        Bundle arguments = getIntent().getExtras();
        counter = 0;

        if(arguments != null) {

            if(getWordsByCategory(arguments.getInt("CATEGORY_ID"))) {

                TextView categoryName = findViewById(R.id.dialogCategoryName);
                categoryName.setText("Категория: " + arguments.getString("CATEGORY_NAME"));

                Fragment fragment = FragmentWordDialog.newInstance(words.get(counter).getName(), String.valueOf(counter));
                counter++;

                replaceFragment(fragment);
            } else{
                 Toast.makeText(this, "Нет слов в данной категории ", Toast.LENGTH_SHORT).show();
            }
        }

        //I know this word
        findViewById(R.id.buttonPositiveAnswer).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                positiveAnswered();
            }
        });

        //I dont know this word
        findViewById(R.id.buttonNegativeAnswer).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                negativeAnswered();
            }
        });
    }

    private void positiveAnswered(){
        if(counter + 1 <= words.size()){
            Fragment fragment = FragmentWordDialog.newInstance(words.get(counter).getName(), String.valueOf(counter));
            counter++;
            replaceFragment(fragment);
        }else{
            Intent intent = new Intent(this, DictionaryActivity.class);
            startActivity(intent);
        }
    }

    private void negativeAnswered(){
        addWord(words.get(counter-1).getId());
        if(counter + 1 <= words.size()){
            Fragment fragment = FragmentWordDialog.newInstance(words.get(counter).getName(), String.valueOf(counter));
            counter++;
            replaceFragment(fragment);
        }else{
            Intent intent = new Intent(this, DictionaryActivity.class);
            startActivity(intent);
        }
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainer, fragment);
        ft.commit();
    }

    private Boolean getWordsByCategory(int categoryId) {

        String wordName;
        int wordId;
        words = new ArrayList<>();

        db = databaseHelper.getReadableDatabase();
        query =  db.rawQuery("SELECT * FROM "+ DatabaseHelper.TABLE_WORDS + " WHERE category_id=" + categoryId, null);

        while (query.moveToNext()){
            wordId = query.getInt(0);
            wordName = query.getString(1);
            words.add(new Word(wordName, wordId));
        }

        db.close();
        query.close();

        if(words.size() > 0) return true;
        return false;
    }

    private long addWord(int wordId) {

        db = databaseHelper.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_USER_ID_FOREIGHN, 1);
        contentValues.put(DatabaseHelper.COLUMN_WORD_ID_FOREIGHN, wordId);

       return db.insert(DatabaseHelper.TABLE_USER_WORDS, null, contentValues);
    }
}