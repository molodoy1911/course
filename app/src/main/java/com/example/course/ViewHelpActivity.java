package com.example.course;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ViewHelpActivity extends AppCompatActivity {
    private HelpDAO helpDAO;
    private TextView tvHelpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_help);

        tvHelpText = findViewById(R.id.tvHelpText);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        helpDAO = new HelpDAO(this);
        helpDAO.open();
        String helpText = helpDAO.getHelpText();
        helpDAO.close();

        if (helpText != null) {
            tvHelpText.setText(helpText);
        }
    }
}
