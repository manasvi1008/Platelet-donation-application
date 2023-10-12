package com.example.plateletapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(selectedId);
            Log.d("MainActivity", "selectedId: " + selectedId);

            if (radioButton != null) {
                Log.d("MainActivity", "Selected radio button ID: " + radioButton.getId());

                if (selectedId == R.id.radioDonor) {
                    Intent intent = new Intent(MainActivity.this, DonorActivity.class);
                    startActivity(intent);
                } else if (selectedId == R.id.radioPatient) {
                    Intent intent = new Intent(MainActivity.this, PatientActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
