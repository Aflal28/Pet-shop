package com.example.dog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Select_option extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option);

        // Admin CardView (CareTaker)
        CardView adminCard = findViewById(R.id.adminCard);
        adminCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to CareTakerActivity
                Intent intent = new Intent(Select_option.this, buyer_login.class);
                startActivity(intent);
            }
        });

        // User CardView (Customer)
        CardView userCard = findViewById(R.id.userCard);
        userCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to CustomerActivity
                Intent intent = new Intent(Select_option.this, seller_login.class);
                startActivity(intent);
            }
        });
    }
}
