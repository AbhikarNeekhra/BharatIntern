package com.example.tempconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText first, second; // Changed TextView to EditText for user input
    RadioGroup radioGroup1, radioGroup2;
    MaterialButton convertButton, clearButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup2 = findViewById(R.id.radioGroup2);
        convertButton = findViewById(R.id.Conver); // Corrected the button ID
        clearButton = findViewById(R.id.Clear);

        // Set click listeners for the buttons
        convertButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.Conver) { // Corrected the button ID
            // Conversion logic
            RadioButton selectedRadioButton1 = findViewById(radioGroup1.getCheckedRadioButtonId());
            RadioButton selectedRadioButton2 = findViewById(radioGroup2.getCheckedRadioButtonId());

            if (selectedRadioButton1 == null || selectedRadioButton2 == null) {
                // If either of the radio groups has no selection, return
                return;
            }

            double inputValue = Double.parseDouble(first.getText().toString());
            double result;

            if (selectedRadioButton1.getId() == R.id.cel1 && selectedRadioButton2.getId() == R.id.far2) {
                // Celsius to Fahrenheit conversion
                result = (inputValue * 9 / 5) + 32;
            } else if (selectedRadioButton1.getId() == R.id.far1 && selectedRadioButton2.getId() == R.id.cel2) {
                // Fahrenheit to Celsius conversion
                result = (inputValue - 32) * 5 / 9;
            } else {
                // Invalid conversion
                return;
            }

            // Display the result in the EditTexts
            second.setText(String.format("%.2f", result));
        } else if (v.getId() == R.id.Clear) {
            // Clear the EditTexts
            first.setText("");
            second.setText("");
        }
    }
}
