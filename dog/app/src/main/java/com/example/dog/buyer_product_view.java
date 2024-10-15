package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class buyer_product_view extends AppCompatActivity {

    private DBhelper dbHelper;
    private ListView petsListView;
    private ArrayList<Pet> petsList;
    private PetNameAdapter adapter;
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_product_view);

        dbHelper = new DBhelper(this);
        petsListView = findViewById(R.id.petsListView);
        searchEditText = findViewById(R.id.searchEditText);

        // Retrieve pet names from the database
        petsList = dbHelper.getAllPetNames();


        // Use a custom adapter to display pet names in the ListView
        adapter = new PetNameAdapter(this, petsList);
        petsListView.setAdapter(adapter);

        // Set a click listener for pet name selection
        petsListView.setOnItemClickListener((parent, view, position, id) -> {
            Pet selectedPet = adapter.getItem(position); // Fetch item from the adapter
            // Navigate to a detailed view passing the selected pet's ID or necessary data
            Intent intent = new Intent(buyer_product_view.this, view_detail_buyer.class);
            intent.putExtra("PET_ID", selectedPet.getId()); // Pass the ID or any necessary data
            startActivity(intent);
        });


        // Set a text change listener for search functionality
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (adapter != null) {
                    adapter.getFilter().filter(s);


                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}
