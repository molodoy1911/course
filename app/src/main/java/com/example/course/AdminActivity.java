package com.example.course;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button btnViewCars = findViewById(R.id.btnViewCars);
        btnViewCars.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, CarsActivity.class);
            startActivity(intent);
        });

        Button btnAddCar = findViewById(R.id.btnAddCar);
        btnAddCar.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, AddCarActivity.class);
            startActivity(intent);
        });
    }
}
