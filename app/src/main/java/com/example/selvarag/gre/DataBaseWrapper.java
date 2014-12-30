package com.example.selvarag.gre;

/**
 * Created by selvarag on 26-12-2014.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseWrapper extends SQLiteOpenHelper {

    public static final String WORDS = "Students";
    public static final String WORDS_ID = "_id";
    public static final String WORD_NAME = "_name";
    public static final String WORD_AGE = "_age";
    public static final String WORD_SYN = "_syn";
    public static final String WORD_EXPL = "_expl";

    private static final String DATABASE_NAME = "Students4.db";
    private static final int DATABASE_VERSION = 1;

    // creation SQLite statement
    private static final String DATABASE_CREATE = "create table " + WORDS
            + "(" + WORDS_ID + " integer primary key autoincrement, "
            + WORD_NAME + " text not null, "+WORD_AGE+" integer, "+WORD_SYN+" text, "+WORD_EXPL+" text);";

    public DataBaseWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you should do some logging in here
        // ..

        db.execSQL("DROP TABLE IF EXISTS " + WORDS);
        onCreate(db);
    }

}