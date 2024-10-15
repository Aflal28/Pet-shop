package com.example.dog;// CaretakerConfirmationActivity.java

import static android.os.Build.VERSION_CODES;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class confirm extends AppCompatActivity {

    private DBhelper dbHelper;
    private TextView petNameTextView, totalPaymentTextView;
    private EditText caretakerNameEditText, contactNumberEditText, addressEditText;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        dbHelper = new DBhelper(this);

        // Find views from the layout
        petNameTextView = findViewById(R.id.petNameTextView);
        totalPaymentTextView = findViewById(R.id.totalPaymentTextView);
        caretakerNameEditText = findViewById(R.id.caretakerNameEditText);
        contactNumberEditText = findViewById(R.id.contactNumberEditText);
        addressEditText = findViewById(R.id.addressEditText);
        confirmButton = findViewById(R.id.confirmButton);

        // Fetch pet details passed from the previous activity
        int petId = getIntent().getIntExtra("PET_ID", -1);
        Pet pet = dbHelper.getPetById(petId);

        // Set retrieved pet details to respective TextViews
        if (pet != null) {
            petNameTextView.setText("Product Name: " + pet.getName());
            int totalPayment = pet.getNote();
            totalPaymentTextView.setText("Total Payment: " + totalPayment);
        }
        // Set click listener for the confirm button
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmCaretaker();
            }
        });
    }

    // Method to handle the confirmation process
    private void confirmCaretaker() {
        String petName = petNameTextView.getText().toString();
        int totalPayment = Integer.parseInt(totalPaymentTextView.getText().toString().replace("Total Payment: ", ""));
        String caretakerName = caretakerNameEditText.getText().toString();
        String contactNumber = contactNumberEditText.getText().toString();
        String address = addressEditText.getText().toString();

        // Validate inputs
        if (caretakerName.isEmpty() || contactNumber.isEmpty()) {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add confirmation details to the database
        dbHelper.addConfirmationDetails(petName,totalPayment,caretakerName, contactNumber, address);

        // Show a success message
        Toast.makeText(this, "Confirmation Successful", Toast.LENGTH_SHORT).show();

        // You can add additional actions as needed, such as navigating to another activity
        // or finishing the current activity.
    }
}
