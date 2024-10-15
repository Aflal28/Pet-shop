package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewFeedbackActivity extends AppCompatActivity {

    private DBhelper dbHelper;
    private ListView feedbackListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);

            dbHelper = new DBhelper(this);
            feedbackListView = findViewById(R.id.feedbackListView);

            // Retrieve feedback details from the database
            ArrayList<String> feedbackList = dbHelper.getAllCareFeedback();

            // Use an ArrayAdapter to display the feedbackList in the ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feedbackList);
            feedbackListView.setAdapter(adapter);
        }
    }