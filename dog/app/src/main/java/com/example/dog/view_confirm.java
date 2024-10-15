package com.example.dog;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dog.databinding.ActivityViewConfirmBinding;

import java.util.ArrayList;

public class view_confirm extends AppCompatActivity {
    private DBhelper dbHelper;
    private ListView petsListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_confirm);

        dbHelper = new DBhelper(this);
        petsListView = findViewById(R.id.petsListView);
        displayConfirm();
    }

    private void displayConfirm() {
        // Fetch the list of confirmation details from the database
        ArrayList<String> confirmList = dbHelper.getAllConfirm();

        // Use an ArrayAdapter to display the list in the ListView
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, confirmList);
        petsListView.setAdapter(arrayAdapter);
    }
}