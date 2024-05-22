package com.example.course;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "course.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE = "role";

    public static final String TABLE_CARS = "cars";
    public static final String COLUMN_CAR_ID = "id";
    public static final String COLUMN_CAR_NAME = "name";
    public static final String COLUMN_CAR_DESCRIPTION = "description";
    public static final String COLUMN_CAR_IMAGE = "image";

    private static final String TABLE_CREATE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT, " +
                    COLUMN_ROLE + " TEXT);";

    private static final String TABLE_CREATE_CARS =
            "CREATE TABLE " + TABLE_CARS + " (" +
                    COLUMN_CAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CAR_NAME + " TEXT, " +
                    COLUMN_CAR_DESCRIPTION + " TEXT, " +
                    COLUMN_CAR_IMAGE + " BLOB);";

    private final Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_USERS);
        db.execSQL(TABLE_CREATE_CARS);

        // Проверяем, существует ли пользователь с ролью администратора
        UserDAO userDAO = new UserDAO(context);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_ROLE + " = 'admin'", null);
        if (cursor.getCount() == 0) {
            // Если нет, создаем нового пользователя с ролью администратора
            ContentValues values = new ContentValues();
            values.put(COLUMN_USERNAME, "admin");
            values.put(COLUMN_PASSWORD, userDAO.hashPassword("admin")); // Пароль администратора по умолчанию "admin"
            values.put(COLUMN_ROLE, "admin");

            db.insert(TABLE_USERS, null, values);
        }
        cursor.close();
        userDAO.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARS);
        onCreate(db);
    }
}
