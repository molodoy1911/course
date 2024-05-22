package com.example.course;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DeleteCarActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DeleteCarsAdapter deleteCarsAdapter;
    private CarDAO carDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_car);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        carDAO = new CarDAO(this);
        carDAO.open();
        List<Car> cars = carDAO.getAllCars();
        carDAO.close();

        deleteCarsAdapter = new DeleteCarsAdapter(cars, carDAO);
        recyclerView.setAdapter(deleteCarsAdapter);

        Button btnBack = findViewById(R.id.back_button);
        btnBack.setOnClickListener(v -> finish());
    }
}
