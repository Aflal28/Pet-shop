package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class buyer_home extends AppCompatActivity {

    CardView  view, user_feed, care_feed, grocCard;
    DBhelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_home);

        view = findViewById(R.id.viewCard);
        user_feed = findViewById(R.id.user_card);
        care_feed = findViewById(R.id.care_card);
        grocCard = findViewById(R.id.confirmBook);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(buyer_home.this, buyer_product_view.class);
                startActivity(intent);

            }
        });



        user_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(buyer_home.this, view_buyer_feedback.class);
                startActivity(intent);

            }
        });
        care_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(buyer_home.this, educational_content.class);
                startActivity(intent);

            }
        });
        grocCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for clothingCard
                // Navigate to another activity (e.g., AddDetailsActivity.class)
                Intent intent = new Intent(buyer_home.this,view_confirm.class);
                startActivity(intent);
            }
        });
    }
}