package com.example.android.habitbuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 *
 * Created by omar on 7/3/16.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "com.example.android.habitbuilder.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    private ContentValues values;

    private static class Entry implements BaseColumns {
        private static final String TABLE_NAME = "habits";
        private static final String ID = "id";
        private static final String COL_NAME = "name";
        private static final String COL_DAYS = "days";
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.values = new ContentValues();
        this.db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + Entry.TABLE_NAME + " ( " + Entry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Entry.COL_NAME + " TEXT NOT NULL," + Entry.COL_DAYS + " INTEGER NOT NULL );";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        // delete any old database
        db.execSQL("DROP TABLE IF EXISTS " + Entry.TABLE_NAME);

        // create a new database
        onCreate(db);
    }

    public boolean insertHabit(String name, String days) {

        values.put(Entry.COL_NAME, name);
        values.put(Entry.COL_DAYS, days);
        long rowId = db.insert(Entry.TABLE_NAME, null, values);
        return (rowId != -1);
    }


    public Cursor getHabits() {
       return db.rawQuery("SELECT * FROM " + Entry.TABLE_NAME, null);
    }

    public void deleteAllHabits() {
        db.delete(Entry.TABLE_NAME, null, null);
    }

    public void updateHabit(String name, String days) {
        values.put(Entry.COL_NAME, name);
        values.put(Entry.COL_DAYS, days);
        db.update(Entry.TABLE_NAME, values, Entry.COL_NAME + " = ?", new String[] {name});
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }
}
