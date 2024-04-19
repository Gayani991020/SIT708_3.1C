package com.example.sit708_task3_1c;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.widget.Toast;
import java.util.Arrays;

public class QuizActivity extends AppCompatActivity {

    // UI components declaration
    private TextView questionTextView;
    private ProgressBar progressBar;
    private Button submitButton;
    private TextView[] optionTextViews = new TextView[4]; // Array to hold option TextViews
    private TextView welcomeTextView;

    // Quiz data variables
    private int questionIndex = 0; // Index to track the current question
    private int score = 0; // Score variable to track user's score
    private int selectedOptionIndex = -1; // Index to track the option selected by the user

    // Arrays to hold quiz questions, options, and correct answers
    private String[] questions = {
            "How many time zones are there in Russia?",
            "What’s the national flower of Japan?",
            "What’s the capital of Canada?",
            "Name the longest river in the world?",
            "Which famous graffiti artist comes from Bristol?",
            "Which artist painted the ceiling of the Sistine Chapel in Rome?"
    };

    private String[][] options = {
            {"11", "12", "13", "14"},
            {"Lilly", "Cherry blossom", "Daisy", "lotus"},
            {"Sydney", "Texas", "Vancouver", "Ottawa"},
            {"Amazon", "nayagara", "The Nile", "Mahaweli"},
            {"Adam", "Banksy", "John", "Sam"},
            {"Michelangelo", "Banksy", "John", "Sam"}
    };

    private String[] correctAnswers = {
            "11",
            "Cherry blossom",
            "Ottawa",
            "The Nile",
            "Banksy",
            "Michelangelo"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz); // Set the layout for the activity

        // Initialize UI components
        initializeViews();

        // Set listeners for option TextViews
        setOptionListeners();

        // Update UI for the first question
        updateQuestion();

        // Set submit button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswerSubmission(); // Handle answer submission
            }
        });
    }

    // Method to initialize UI components
    private void initializeViews() {
        questionTextView = findViewById(R.id.questionTitleTextView); // Find and assign the question TextView
        progressBar = findViewById(R.id.progressBar); // Find and assign the progress bar
        submitButton = findViewById(R.id.submitButton); // Find and assign the submit button
        welcomeTextView = findViewById(R.id.welcomeTextView); // Find and assign the welcome message TextView

        // Find and assign option TextViews to the array
        optionTextViews[0] = findViewById(R.id.option1TextView);
        optionTextViews[1] = findViewById(R.id.option2TextView);
        optionTextViews[2] = findViewById(R.id.option3TextView);
        optionTextViews[3] = findViewById(R.id.option4TextView);

        // Display a welcome message with the user's name
        String userName = getIntent().getStringExtra("USER_NAME");
        welcomeTextView.setText(getString(R.string.welcome_user, userName));
    }

    // Method to set onClickListeners for option TextViews
    private void setOptionListeners() {
        for (int i = 0; i < optionTextViews.length; i++) {
            final int index = i;
            optionTextViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOptionSelected(index); // Handle the selection of an option
                }
            });
        }
    }

    // Method to handle answer submission
    private void handleAnswerSubmission() {
        if (selectedOptionIndex == -1) {
            // No answer selected, show a toast message prompting the user to select an answer
            Toast.makeText(QuizActivity.this, "Please select an answer.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isCorrect = checkAnswer(); // Check if the selected answer is correct

        if (questionIndex < questions.length - 1) {
            submitButton.setText(R.string.next_question); // Change button text for next question
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetOptionViews(); // Reset option views
                    questionIndex++; // Move to the next question
                    updateQuestion(); // Update UI with the next question
                    submitButton.setText(R.string.submit); // Reset button text

                    // Set onClickListener again for answer submission
                    submitButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            handleAnswerSubmission();
                        }
                    });
                }
            });
        } else if (questionIndex == questions.length - 1) {
            // Last question
            if (isCorrect || !isCorrect) {
                submitButton.setText(R.string.finish_quiz); // Change button text for finishing quiz
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showResults(); // Show quiz results
                    }
                });
            }
        }
    }

    // Method to handle selection of an option
    private void onOptionSelected(int index) {
        selectedOptionIndex = index; // Set the selected option index
        for (int i = 0; i < optionTextViews.length; i++) {
            optionTextViews[i].setSelected(i == index); // Highlight the selected option
        }
    }

    // Method to check if the selected answer is correct
    private boolean checkAnswer() {
        // Get the index of the correct answer
        int correctIndex = Arrays.asList(correctAnswers).indexOf(correctAnswers[questionIndex]);
        // Check if the selected option matches the correct answer
        boolean isCorrect = options[questionIndex][selectedOptionIndex].equals(correctAnswers[questionIndex]);

        // Update UI to show correct and incorrect answers
        for (int i = 0; i < optionTextViews.length; i++) {
            if (i == correctIndex) {
                optionTextViews[i].setBackgroundColor(ContextCompat.getColor(this, R.color.correct_answer_color)); // Highlight correct answer
            } else {
                optionTextViews[i].setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent)); // Reset background color
            }

            if (i == selectedOptionIndex && !isCorrect) {
                optionTextViews[i].setBackgroundColor(ContextCompat.getColor(this, R.color.incorrect_answer_color)); // Highlight incorrect answer
            }

            optionTextViews[i].setClickable(false); // Disable further selections
        }

        if (isCorrect) {
            score++; // Increment score for correct answer
        }

        return isCorrect;
    }

    // Method to reset option views for the next question
    private void resetOptionViews() {
        for (TextView optionView : optionTextViews) {
            optionView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent)); // Reset background color
            optionView.setClickable(true); // Make options clickable again
        }
    }

    // Method to update UI with the current question
    private void updateQuestion() {
        questionTextView.setText(questions[questionIndex]); // Set current question text
        for (int i = 0; i < optionTextViews.length; i++) {
            optionTextViews[i].setText(options[questionIndex][i]); // Set options text
            optionTextViews[i].setBackgroundResource(R.drawable.answer_selector); // Set background resource
            optionTextViews[i].setClickable(true); // Make options clickable
            optionTextViews[i].setSelected(false); // Deselect options
        }
        progressBar.setProgress((questionIndex + 1) * 100 / questions.length); // Update progress bar
        selectedOptionIndex = -1; // Reset selected option index
    }

    // Method to display quiz results
    private void showResults() {
        Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
        intent.putExtra("SCORE", score); // Pass the score to the ScoreActivity
        intent.putExtra("TOTAL_QUESTIONS", questions.length); // Pass total questions count
        intent.putExtra("USER_NAME", getIntent().getStringExtra("USER_NAME")); // Pass the user's name
        startActivity(intent); // Start ScoreActivity
        finish(); // Finish QuizActivity
    }
}