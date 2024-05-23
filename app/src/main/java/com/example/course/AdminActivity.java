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

        Button btnAddCar = findViewById(R.id.btnAddCar);
        btnAddCar.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, AddCarActivity.class);
            startActivity(intent);
        });

        Button btnViewCars = findViewById(R.id.btnViewCars);
        btnViewCars.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, CarsActivity.class);
            startActivity(intent);
        });

        Button btnDeleteCars = findViewById(R.id.btnDeleteCars);
        btnDeleteCars.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, DeleteCarActivity.class);
            startActivity(intent);
        });

        Button btnViewHelp = findViewById(R.id.btnViewHelp);
        btnViewHelp.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, ViewHelpActivity.class);
            startActivity(intent);
        });

        Button btnEditHelp = findViewById(R.id.btnEditHelp);
        btnEditHelp.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, EditHelpActivity.class);
            startActivity(intent);
        });
    }
}
