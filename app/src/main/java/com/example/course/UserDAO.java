package com.example.course;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserDAO {
    private Context context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public UserDAO(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean addUser(String username, String password, String role) {
        if (getUserByUsername(username) != null) {
            return false;
        }

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PASSWORD, hashPassword(password));
        values.put(DatabaseHelper.COLUMN_ROLE, role);

        long result = database.insert(DatabaseHelper.TABLE_USERS, null, values);
        return result != -1;
    }

    public User getUserByUsername(String username) {
        Cursor cursor = database.query(DatabaseHelper.TABLE_USERS,
                null,
                DatabaseHelper.COLUMN_USERNAME + " = ?",
                new String[]{username},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD)));
            user.setRole(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ROLE)));
            cursor.close();
            return user;
        }

        return null;
    }

    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
