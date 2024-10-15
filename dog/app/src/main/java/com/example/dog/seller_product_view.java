package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class seller_product_view extends AppCompatActivity {

    private DBhelper dbHelper;
    private ListView petsListView;
    private ArrayList<Pet> petsList;
    private PetNameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_product_view);

        dbHelper = new DBhelper(this);
        petsListView = findViewById(R.id.petsListView);

        // Retrieve pet names from the database
        petsList = dbHelper.getAllPetNames();

        // Use a custom adapter to display pet names in the ListView
        adapter = new PetNameAdapter(this, petsList);
        petsListView.setAdapter(adapter);

        // Set a click listener for pet name selection
        petsListView.setOnItemClickListener((parent, view, position, id) -> {
            Pet selectedPet = adapter.getItem(position); // Fetch item from the adapter
            // Navigate to a detailed view passing the selected pet's ID or necessary data
            Intent intent = new Intent(seller_product_view.this,view_detail_seller.class);
            intent.putExtra("PET_ID", selectedPet.getId()); // Pass the ID or any necessary data
            startActivity(intent);
        });


        // Set a text change listener for search functionality
    }
}
