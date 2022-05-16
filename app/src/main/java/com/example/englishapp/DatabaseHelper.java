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

    //Topics
    static final String TABLE_TOPICS = "topics"; // название таблицы в бд

    public static final String COLUMN_TOPIC_ID = "topic_id";
    public static final String COLUMN_TOPIC_TITLE = "topic_title";
    public static final String COLUMN_TOPIC_BODY = "topic_body";
    public static final String COLUMN_TOPIC_SERIAL_NUMBER = "topic_serial_number";
    //End Topics

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Categories table
        //Create table
        db.execSQL("CREATE TABLE "+ TABLE_CATEGORIES +" (" +
                COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_CATEGORY_NAME + " TEXT);");
        // Inserting data to the Categories table
        db.execSQL("INSERT INTO "+ TABLE_CATEGORIES +" (" +
                COLUMN_CATEGORY_NAME + ") "
                + "VALUES ('Города'), ('Животные'), ('Глаголы'), ('Существительные');");
        //END Categories table

        //Words table
        //Create table
        db.execSQL("CREATE TABLE "+ TABLE_WORDS +" (" +
                COLUMN_WORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_WORD_NAME + " TEXT);");
        // Inserting data to the Words table
        db.execSQL("INSERT INTO "+ TABLE_WORDS +" (" +
                COLUMN_WORD_NAME + ") " +
                "VALUES ('To run'), ('To eat'), ('Current'), ('Apple');");
        //END Words table

        //Topics table
        //Create table
        db.execSQL("CREATE TABLE "+ TABLE_TOPICS +" (" +
                COLUMN_TOPIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TOPIC_TITLE + " TEXT," +
                COLUMN_TOPIC_BODY + " TEXT," +
                COLUMN_TOPIC_SERIAL_NUMBER + " TEXT);");
        // Inserting data to the Topics table
        db.execSQL("INSERT INTO "+ TABLE_TOPICS + "( " +
                COLUMN_TOPIC_TITLE + "," +
                COLUMN_TOPIC_BODY + "," +
                COLUMN_TOPIC_SERIAL_NUMBER + ")" +
                " VALUES " +
                "('Построение предложений в английском языке I/We/You/They', 'Общая формула для построения утвердительных предложений в простом настоящем времени в английском языке: Подлежащее + глагол (сказуемое) + ...', '1')," +
                "('Артикль The', 'The — определенный артикль. Обозначает «этот», «эти», то есть указывает на что-то конкретное. Употребляется с любыми существительными: исчисляемыми и неисчисляемыми существительными, единственного и множественного числа.', '2')," +
                "('Title1', 'Body1', '3')," +
                "('Title1', 'Body1', '4')," +
                "('Title1', 'Body1', '5')," +
                "('Title1', 'Body1', '6')," +
                "('Title1', 'Body1', '7');");
        //END Topics table
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_WORDS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TOPICS);
        onCreate(db);
    }

}
