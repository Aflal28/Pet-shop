package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home_seller extends AppCompatActivity {

    // Declare your CardViews and other views here
    CardView adduser, add_pets, view, beautyCard, care_card, grocCard;
    DBhelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_seller);

        // Initialize your CardViews
        adduser = findViewById(R.id.clothingCard);
        add_pets = findViewById(R.id.elecCard);
        view = findViewById(R.id.viewCard);
        beautyCard = findViewById(R.id.beautyCard);
        care_card = findViewById(R.id.caretake_card);
        grocCard = findViewById(R.id.confirmBook);

        // Set click listeners for each CardView
        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for clothingCard
                // Navigate to another activity (e.g., AddDetailsActivity.class)
                Intent intent = new Intent(Home_seller.this,ManageDetails.class);
                startActivity(intent);
            }
        });

        add_pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for elecCard
                // Navigate to another activity (e.g., AddPetsActivity.class)
                Intent intent = new Intent(Home_seller.this, Add_product.class);
                startActivity(intent);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_seller.this, seller_product_view.class);
                startActivity(intent);

            }
        });
        beautyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for elecCard
                // Navigate to another activity (e.g., AddPetsActivity.class)
                Intent intent = new Intent(Home_seller.this, feedback.class);
                startActivity(intent);
            }
        });
        care_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home_seller.this, ViewFeedbackActivity.class);
                startActivity(intent);

            }
        });
        grocCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for clothingCard
                // Navigate to another activity (e.g., AddDetailsActivity.class)
                Intent intent = new Intent(Home_seller.this,view_confirm.class);
                startActivity(intent);
            }
        });



    }
}
