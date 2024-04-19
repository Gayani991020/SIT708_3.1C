package com.example.sit708_task3_1c;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class CalculatorActivity extends AppCompatActivity {

    private EditText firstNumberEditText, secondNumberEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Initialize UI elements
        firstNumberEditText = findViewById(R.id.firstNumberEditText);
        secondNumberEditText = findViewById(R.id.secondNumberEditText);
        Button addButton = findViewById(R.id.addButton);
        Button subtractButton = findViewById(R.id.subtractButton);
        resultTextView = findViewById(R.id.resultTextView);
        Button backToMainButton = findViewById(R.id.backToMainButton);

        // Set onClickListeners for the buttons
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get numbers from EditText fields and perform addition
                double num1 = Double.parseDouble(firstNumberEditText.getText().toString());
                double num2 = Double.parseDouble(secondNumberEditText.getText().toString());
                double result = num1 + num2;
                resultTextView.setText("Result: " + result);
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get numbers from EditText fields and perform subtraction
                double num1 = Double.parseDouble(firstNumberEditText.getText().toString());
                double num2 = Double.parseDouble(secondNumberEditText.getText().toString());
                double result = num1 - num2;
                resultTextView.setText("Result: " + result);
            }
        });

        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the MainActivity
                Intent intent = new Intent(CalculatorActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}