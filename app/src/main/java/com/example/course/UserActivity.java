package com.example.course;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Button btnViewCars = findViewById(R.id.btnViewCars);
        btnViewCars.setOnClickListener(v -> {
            Intent intent = new Intent(UserActivity.this, CarsActivity.class);
            startActivity(intent);
        });

        Button btnViewHelp = findViewById(R.id.btnViewHelp);
        btnViewHelp.setOnClickListener(v -> {
            Intent intent = new Intent(UserActivity.this, ViewHelpActivity.class);
            startActivity(intent);
        });
    }
}
