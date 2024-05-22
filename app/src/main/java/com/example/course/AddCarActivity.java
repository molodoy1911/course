package com.example.course;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddCarActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText etName;
    private EditText etDescription;
    private ImageView ivImage;
    private byte[] carImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        ivImage = findViewById(R.id.ivImage);

        Button btnSelectImage = findViewById(R.id.btnSelectImage);
        btnSelectImage.setOnClickListener(v -> openImageChooser());

        Button btnAddCar = findViewById(R.id.btnAddCar);
        btnAddCar.setOnClickListener(v -> addCar());

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ivImage.setImageBitmap(bitmap);
                carImage = bitmapToByteArray(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private void addCar() {
        String name = etName.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (name.isEmpty() || description.isEmpty() || carImage == null) {
            Toast.makeText(this, "Please fill in all fields and select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        CarDAO carDAO = new CarDAO(this);
        carDAO.open();
        boolean success = carDAO.addCar(name, description, carImage);
        carDAO.close();

        if (success) {
            Toast.makeText(this, "Car added successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error adding car", Toast.LENGTH_SHORT).show();
        }
    }
}
