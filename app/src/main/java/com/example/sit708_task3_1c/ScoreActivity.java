package com.example.sit708_task3_1c;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



public class ScoreActivity extends AppCompatActivity {

    // Declare UI components
    private TextView scoreTextView;
    private Button takeNewQuizButton, finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score); // Set the layout for this activity

        // Initialize UI components by finding their respective views in the layout XML
        scoreTextView = findViewById(R.id.scoreTextView);
        takeNewQuizButton = findViewById(R.id.takeNewQuizButton);
        finishButton = findViewById(R.id.finishButton);

        // Get data passed from the QuizActivity via Intent
        String userName = getIntent().getStringExtra("USER_NAME");
        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);

        // Set up TextView to display a congratulatory message with the user's name
        TextView congratulationsTextView = findViewById(R.id.congratulationsTextView);
        congratulationsTextView.setText("Congratulations " + userName + "!");

        // Set the score TextView to display the user's score out of total questions
        scoreTextView.setText("Your Score: " + score + "/" + totalQuestions);

        // Set onClickListener for the "Take New Quiz" button
        takeNewQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the MainActivity (to restart the quiz)
                Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
                startActivity(intent); // Start the MainActivity
                finish(); // Finish the ScoreActivity to prevent going back to it using the back button
            }
        });

        // Set onClickListener for the "Finish" button
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finish the ScoreActivity to quit the quiz
            }
        });
    }
}
