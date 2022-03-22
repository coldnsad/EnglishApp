package com.example.englishapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EnglishApp.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных

    //Categories
    static final String TABLE_CATEGORIES = "categories"; // название таблицы в бд

    public static final String COLUMN_CATEGORY_ID = "category_id";
    public static final String COLUMN_CATEGORY_NAME = "category_name";
    //End Categories

    //Words
    static final String TABLE_WORDS = "words"; // название таблицы в бд

    public static final String COLUMN_WORD_ID = "word_id";
    public static final String COLUMN_WORD_NAME = "word_name";
    public static final String COLUMN_WORD_CATEGORY_ID = COLUMN_CATEGORY_ID;
    //End Words

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create Categories table
        db.execSQL("CREATE TABLE "+ TABLE_CATEGORIES +" (" + COLUMN_CATEGORY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CATEGORY_NAME
                + " TEXT);");
        // добавление начальных данных
        db.execSQL("INSERT INTO "+ TABLE_CATEGORIES +" (" + COLUMN_CATEGORY_NAME + ") "
                + "VALUES ('Города'), ('Животные'), ('Глаголы'), ('Существительные');");
        //END Create Categories table
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CATEGORIES);
        onCreate(db);
    }

}
