package com.example.course;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase database;

    public CarDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean addCar(String name, String description, byte[] image) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_CAR_NAME, name);
        values.put(DatabaseHelper.COLUMN_CAR_DESCRIPTION, description);
        values.put(DatabaseHelper.COLUMN_CAR_IMAGE, image);

        long result = database.insert(DatabaseHelper.TABLE_CARS, null, values);
        Log.d("CarDAO", "Insert result: " + result);
        return result != -1;
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_CARS,
                null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Car car = new Car();
                car.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_CAR_ID)));
                car.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CAR_NAME)));
                car.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CAR_DESCRIPTION)));
                car.setImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_CAR_IMAGE)));
                cars.add(car);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return cars;
    }
}
