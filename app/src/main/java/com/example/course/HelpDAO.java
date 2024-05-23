package com.example.course;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HelpDAO {
    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase database;

    public HelpDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean addHelpText(String text) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_HELP_TEXT, text);

        long result = database.insert(DatabaseHelper.TABLE_HELP, null, values);
        return result != -1;
    }

    public boolean updateHelpText(int id, String text) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_HELP_TEXT, text);

        int result = database.update(DatabaseHelper.TABLE_HELP, values,
                DatabaseHelper.COLUMN_HELP_ID + " = ?",
                new String[]{String.valueOf(id)});
        return result > 0;
    }

    public String getHelpText() {
        String helpText = null;
        Cursor cursor = database.query(DatabaseHelper.TABLE_HELP,
                new String[]{DatabaseHelper.COLUMN_HELP_TEXT},
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            helpText = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_HELP_TEXT));
            cursor.close();
        }

        return helpText;
    }
}
