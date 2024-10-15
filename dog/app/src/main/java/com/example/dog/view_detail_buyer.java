package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class view_detail_buyer extends AppCompatActivity {

    private DBhelper dbHelper;
    private TextView petNameTextView, petDescriptionTextView, petAgeTextView,
            petSexTextView, petNoteTextView;
    private ImageView petImageView;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail_buyer);

        dbHelper = new DBhelper(this);

        // Find TextViews from the layout
        petNameTextView = findViewById(R.id.petNameTextView);
        petDescriptionTextView = findViewById(R.id.petDescriptionTextView);
        petAgeTextView = findViewById(R.id.petAgeTextView);
        petSexTextView = findViewById(R.id.petSexTextView);
        petNoteTextView = findViewById(R.id.petNoteTextView);
        petImageView = findViewById(R.id.petImageView);
        confirm = findViewById(R.id.attractiveButton);



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
            } else {
                // Set a placeholder image or handle empty image accordingly
            }
          confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the confirm button click
                navigateToCaretakerConfirmationPage(petId);
            }
        });
    } else {
        Toast.makeText(view_detail_buyer.this, "Details Not Found", Toast.LENGTH_SHORT).show();
    }
}

    // Method to navigate to the CaretakerConfirmationActivity
    private void navigateToCaretakerConfirmationPage(int petId) {
        Intent intent = new Intent(view_detail_buyer.this, confirm.class);
        intent.putExtra("PET_ID", petId);
        startActivity(intent);
    }
}