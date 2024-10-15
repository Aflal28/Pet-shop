package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class view_detail_seller extends AppCompatActivity {

    private DBhelper dbHelper;
    private TextView petNameTextView, petDescriptionTextView, petAgeTextView, petSexTextView, petNoteTextView;
    private ImageView petImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail_seller);

        dbHelper = new DBhelper(this);

        // Find TextViews from the layout
        petNameTextView = findViewById(R.id.petNameTextView);
        petDescriptionTextView = findViewById(R.id.petDescriptionTextView);
        petAgeTextView = findViewById(R.id.petAgeTextView);
        petSexTextView = findViewById(R.id.petSexTextView);
        petNoteTextView = findViewById(R.id.petNoteTextView);
        petImageView = findViewById(R.id.petImageView);



        // Fetch pet details using the ID passed from the previous activity
        int petId = getIntent().getIntExtra("PET_ID", -1);
        Pet pet = dbHelper.getPetById(petId);

        // Set retrieved pet details to respective TextViews
        if (pet != null) {
            petNameTextView.setText("Name: " + pet.getName());
            petDescriptionTextView.setText("Description: " + pet.getDescription());
            petAgeTextView.setText("Age: " + pet.getAge());
            petSexTextView.setText("Sex: " + pet.getSex());
            petNoteTextView.setText("Payment: " + pet.getNote());
            int totalPayment = pet.getNote();
            TextView totalPaymentTextView = findViewById(R.id.totalPaymentTextView);
            totalPaymentTextView.setText("Total Payment: " + totalPayment);

            if (pet.getImage() != null) {
                petImageView.setImageBitmap(pet.getImage());
            }

        } else {
            Toast.makeText(view_detail_seller.this, "Details Not Found", Toast.LENGTH_SHORT).show();
        }
    }
}