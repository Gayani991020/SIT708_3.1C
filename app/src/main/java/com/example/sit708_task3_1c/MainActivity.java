package com.example.sit708_task3_1c;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private Button startButton, openCalculatorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        nameEditText = findViewById(R.id.nameEditText);
        startButton = findViewById(R.id.startButton);
        openCalculatorButton = findViewById(R.id.openCalculatorButton);

        // Set listener for the start button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the name entered by the user
                String name = nameEditText.getText().toString();
                // Create an intent to start QuizActivity
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                // Pass the user's name to QuizActivity
                intent.putExtra("USER_NAME", name);
                // Start QuizActivity
                startActivity(intent);
            }
        });

        // Set listener for the calculator button
        openCalculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start CalculatorActivity
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                // Start CalculatorActivity
                startActivity(intent);
            }
        });
    }
}
