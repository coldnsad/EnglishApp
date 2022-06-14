package com.example.englishapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EnglishApp.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных

    //Topics
    static final String TABLE_TOPICS = "topics"; // название таблицы в бд

    public static final String COLUMN_TOPIC_ID = "topic_id";
    public static final String COLUMN_TOPIC_TITLE = "topic_title";
    public static final String COLUMN_TOPIC_BODY = "topic_body";
    public static final String COLUMN_TOPIC_SERIAL_NUMBER = "topic_serial_number";
    //End Topics

    //Categories
    static final String TABLE_CATEGORIES = "categories"; // название таблицы в бд

    public static final String COLUMN_CATEGORY_ID = "category_id";
    public static final String COLUMN_CATEGORY_NAME = "category_name";
    //End Categories

    //Words
    static final String TABLE_WORDS = "words"; // название таблицы в бд

    public static final String COLUMN_WORD_ID = "word_id";
    public static final String COLUMN_WORD_NAME = "word_name";
    public static final String COLUMN_WORD_TRANSLATION = "word_translation";
    public static final String COLUMN_WORD_TRANSCRIPTION = "word_transcription";
    public static final String COLUMN_WORD_CATEGORY_ID = COLUMN_CATEGORY_ID;
    //End Words

    //User Words
    static final String TABLE_USER_WORDS = "user_words"; // название таблицы в бд

    public static final String COLUMN_USER_WORD_ID = "user_word_id";
    public static final String COLUMN_USER_ID_FOREIGHN = "user_id";
    public static final String COLUMN_WORD_ID_FOREIGHN = COLUMN_WORD_ID;
    //End User Words



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

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
                "('Present Simple - простое настоящее время', 'Время Present Simple обозначает действие в настоящем в широком смысле слова. Оно употребляется для обозначения обычных, регулярно повторяющихся или постоянных действий, например, когда мы говорим о чьих-либо привычках, режиме дня, расписании и т. д., т. е. Present Simple обозначает действия, которые происходят в настоящее время, но не привязаны именно к моменту речи.', '3')," +
                "('Past Simple - простое прошедшее время', 'Время Past Simple используется для обозначения действия, которое произошло в определенное время в прошлом и время совершения которого уже истекло.\n" +
                "\n" +
                "Для уточнения момента совершения действия в прошлом при использовании времени Past Simple обычно используются такие слова, как:\n" +
                "\n" +
                "- five days ago – пять дней назад\n" +
                "- last year – в прошлом году', '4')," +
                "('Future Simple - простое будущее время', 'Время Future Simple ссылается на действие, которое совершится в неопределенном или отдаленном будущем.\n" +
                "\n" +
                "Простое будущее время в английском языке обычно используется с обстоятельствами:\n" +
                "\n" +
                "- tomorrow – завтра\n" +
                "- next year – в следующем году\n" +
                "- in five years – через пять лет\n" +
                "- in 2095 – в 2095 году', '5')," +
                "('Активный залог (active voice)', 'Категория залога показывает, производит ли действие лицо (предмет), выраженное существительным или местоимением в функции подлежащего, или же оно само испытывает на себе чье-либо действие.\n" +
                "\n" +
                "В английском языке существует два вида залога: активный залог (действительный) и пассивный залог (страдательный).\n" +
                "\n" +
                "Активный залог (active voice) показывает, что лицо или предмет, выраженное подлежащим, само производит действие:\n" +
                "\n" +
                "Sam baked a big cake.\n" +
                "Сэм испек большой пирог.', '6')," +
                "('Пассивный залог (passive voice)', 'Форма залога показывает, является ли подлежащее в предложении (лицо или предмет) производителем или объектом действия, выраженного сказуемым.\n" +
                "\n" +
                "Пассивный залог (passive voice) показывает, что лицо или предмет, выраженное подлежащим, испытывает действие на себе:\n" +
                "\n" +
                "The big cake was baked by Sam.\n" +
                "Большой пирог был испечен Сэмом.', '7');");
        //END Topics table

        //Categories table
        //Create table
        db.execSQL("CREATE TABLE "+ TABLE_CATEGORIES +" (" +
                COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_CATEGORY_NAME + " TEXT);");
        // Inserting data to the Categories table
        db.execSQL("INSERT INTO "+ TABLE_CATEGORIES +" (" +
                COLUMN_CATEGORY_NAME + ") "
                + "VALUES " +
                "('Человек'), " +
                "('Животные'), " +
                "('Глаголы'), " +
                "('Существительные');");
        //END Categories table

        //Words table
        //Create table
        db.execSQL("CREATE TABLE "+ TABLE_WORDS +" (" +
                COLUMN_WORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_WORD_NAME + " TEXT," +
                COLUMN_WORD_TRANSLATION + " TEXT," +
                COLUMN_WORD_TRANSCRIPTION + " TEXT," +
                COLUMN_WORD_CATEGORY_ID + " INTEGER);");
        // Inserting data to the Words table
        db.execSQL("INSERT INTO "+ TABLE_WORDS +" (" +
                COLUMN_WORD_NAME + "," +
                COLUMN_WORD_CATEGORY_ID + "," +
                COLUMN_WORD_TRANSLATION + "," +
                COLUMN_WORD_TRANSCRIPTION + ") " +
                "VALUES " +
                "('Body', '1', 'Тело', '[ˈbɑːdɪ]'), " +
                "('Vision', '1', 'Зрение', '[ˈvɪʒn]');");
        //END Words table
        
        //User words table
        //Create table
        db.execSQL("CREATE TABLE "+ TABLE_USER_WORDS +" (" +
                COLUMN_USER_WORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USER_ID_FOREIGHN + " INTEGER," +
                COLUMN_WORD_ID_FOREIGHN + " INTEGER);");
        // Inserting data to the Words table
        db.execSQL("INSERT INTO "+ TABLE_USER_WORDS +" (" +
                COLUMN_USER_ID_FOREIGHN + "," +
                COLUMN_WORD_ID_FOREIGHN + ") " +
                "VALUES " +
                "('1', '2');");
        //END User words table


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        onCreate(db);
    }

}
