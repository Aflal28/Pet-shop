package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class feedback extends AppCompatActivity {

    // Declare DBHelper
    private DBhelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // Initialize DBHelper
        dbHelper = new DBhelper(this);

        // Find your Submit button by its ID
        Button buttonSubmitFeedback = findViewById(R.id.buttonSubmitFeedback);

        // Set click listener for the Submit button
        buttonSubmitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the rating and comment values from your UI elements
                RatingBar ratingBar = findViewById(R.id.ratingBarFeedback);
                EditText editText = findViewById(R.id.editTextFeedback);

                double rating = ratingBar.getRating();
                String comment = editText.getText().toString();

                // Add feedback to the database
                dbHelper.addFeedback(rating, comment);

                // Clear input fields or perform any other necessary actions
                ratingBar.setRating(0);
                editText.setText("");
                if (rating > 0 && !comment.isEmpty()) {
                    Toast.makeText(feedback.this, "Feedback Added Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(feedback.this, "Failed to add feedback. Please provide valid input.", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
