package com.example.course;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditHelpActivity extends AppCompatActivity {
    private HelpDAO helpDAO;
    private EditText etHelpText;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_help);

        etHelpText = findViewById(R.id.etHelpText);
        btnSave = findViewById(R.id.btnSave);

        helpDAO = new HelpDAO(this);
        helpDAO.open();
        String helpText = helpDAO.getHelpText();
        helpDAO.close();

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        if (helpText != null) {
            etHelpText.setText(helpText);
        }

        btnSave.setOnClickListener(v -> {
            String newText = etHelpText.getText().toString();
            helpDAO.open();
            boolean success;
            if (helpText == null) {
                success = helpDAO.addHelpText(newText);
            } else {
                success = helpDAO.updateHelpText(1, newText); // Assuming there's only one help text
            }
            helpDAO.close();

            if (success) {
                Toast.makeText(EditHelpActivity.this, "Help text saved", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(EditHelpActivity.this, "Error saving help text", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
