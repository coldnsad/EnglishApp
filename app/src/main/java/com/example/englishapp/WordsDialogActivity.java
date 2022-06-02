package com.example.englishapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class WordsDialogActivity extends AppCompatActivity {

    private List<Word> words;
    private int counter;
    private Word currentWord;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDB;
    private Cursor query;
    private ContentValues contentValues;

    private FirebaseDatabase firebaseDB;
    private String dbLink = "https://englishapp-df661-default-rtdb.asia-southeast1.firebasedatabase.app";
    private DatabaseReference userWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_dialog);
        databaseHelper = new DatabaseHelper(this);
        firebaseDB = FirebaseDatabase.getInstance(dbLink);
        userWords = firebaseDB.getReference("User_words");

        Bundle arguments = getIntent().getExtras();
        counter = 0;

        if(arguments != null) {

            if(getWordsByCategory(arguments.getInt("CATEGORY_ID"))) {
                currentWord = words.get(counter);
                TextView categoryName = findViewById(R.id.dialogCategoryName);
                categoryName.setText("Категория: " + arguments.getString("CATEGORY_NAME"));

                Fragment fragment = FragmentWordDialog.newInstance(
                        currentWord.getName(),
                        currentWord.getTranslation(),
                        currentWord.getTranscription());
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
            currentWord = words.get(counter);
            Fragment fragment = FragmentWordDialog.newInstance(
                    currentWord.getName(),
                    currentWord.getTranslation(),
                    currentWord.getTranscription());
            counter++;
            replaceFragment(fragment);
        }else{
            Intent intent = new Intent(this, DictionaryActivity.class);
            startActivity(intent);
        }
    }

    private void negativeAnswered(){
        addWord(words.get(counter-1));
        if(counter + 1 <= words.size()){
            currentWord = words.get(counter);
            Fragment fragment = FragmentWordDialog.newInstance(
                    currentWord.getName(),
                    currentWord.getTranslation(),
                    currentWord.getTranscription());
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
        String translation;
        String transcription;
        int wordId;
        words = new ArrayList<>();

        sqLiteDB = databaseHelper.getReadableDatabase();
        query =  sqLiteDB.rawQuery("SELECT * FROM "+ DatabaseHelper.TABLE_WORDS + " WHERE category_id=" + categoryId, null);

        while (query.moveToNext()){
            wordId = query.getInt(0);
            wordName = query.getString(1);
            translation = query.getString(2);
            transcription = query.getString(3);

            words.add(new Word(wordName, translation, transcription, wordId));
        }

        sqLiteDB.close();
        query.close();

        if(words.size() > 0) return true;
        return false;
    }

    private void addWord(Word word) {

        userWords.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .push()
                .setValue(word)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Snackbar.make(findViewById(R.id.rootDialogActivity), "Done", Snackbar.LENGTH_SHORT)
                                .show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(findViewById(R.id.rootDialogActivity), "Fail: " + e.getMessage(), Snackbar.LENGTH_SHORT)
                        .show();
            }
        });
       /* sqLiteDB = databaseHelper.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_USER_ID_FOREIGHN, 1);
        contentValues.put(DatabaseHelper.COLUMN_WORD_ID_FOREIGHN, wordId);

       return sqLiteDB.insert(DatabaseHelper.TABLE_USER_WORDS, null, contentValues);*/
    }
}