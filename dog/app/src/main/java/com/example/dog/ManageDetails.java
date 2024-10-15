package com.example.dog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ManageDetails extends AppCompatActivity {

    private EditText editTextPetName, editTextDescription, editTextAge, editTextSex, editTextNote;
    private DBhelper dbHelper;
    ImageView petImageView;
    private Button btnUpdate, btnDelete,btnSearch;
    private static final int PICK_IMAGE = 1;
    private int petId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_details);
        dbHelper = new DBhelper(this);

        editTextPetName = findViewById(R.id.editTextPetName);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextAge = findViewById(R.id.editTextAge);
        editTextSex = findViewById(R.id.editTextSex);
        editTextNote = findViewById(R.id.editTextNote);
        petImageView = findViewById(R.id.petImageView);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
        btnSearch = findViewById(R.id.buttonSearch);


                // Set click listeners for buttons
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updatePet();
                    }
                });

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deletePet();
                    }
                });
                petImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openGallery();
                    }
                });
                btnSearch.setOnClickListener(new View.OnClickListener() {
               @Override
            public void onClick(View v) {
                searchPet();
            }
        });
    }

    private void updatePet() {
        // Retrieve edited details from EditTexts
        String name = editTextPetName.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        int age = Integer.parseInt(editTextAge.getText().toString().trim());
        String sex = editTextSex.getText().toString().trim();
        int note = Integer.parseInt(editTextNote.getText().toString().trim());

        byte[] updatedImage = convertImageToByteArray(petImageView);

        // Update the pet details in the database
        int numRowsAffected = dbHelper.updatePet( name, description, age, sex, note, updatedImage);

        if (numRowsAffected > 0) {
            // Display a success message
            Toast.makeText(this, "Pet details updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            // Display a failure message
            Toast.makeText(this, "Failed to update pet details", Toast.LENGTH_SHORT).show();
        }
    }

    private void deletePet() {
        // Delete the pet from the database
        dbHelper.deletePet(editTextPetName.getText().toString().trim());

        // Display a success message
        Toast.makeText(this, "Pet deleted successfully", Toast.LENGTH_SHORT).show();

        // Navigate back to the list of pets or any other appropriate activity
        startActivity(new Intent(ManageDetails.this, Home_seller.class));
        finish(); // Close the current activity
    }


    private void searchPet() {
        // Retrieve pet details based on the entered pet name
        String petName = editTextPetName.getText().toString().trim();
        Pet pet = dbHelper.getPetByName(petName);

        if (pet != null) {
            // If pet found, update the UI with the details
            editTextDescription.setText(pet.getDescription());
            editTextAge.setText(String.valueOf(pet.getAge()));
            editTextSex.setText(pet.getSex());
            editTextNote.setText(String.valueOf(pet.getNote()));
            petImageView.setImageBitmap(pet.getImage());

            Toast.makeText(this, "Pet found!", Toast.LENGTH_SHORT).show();
        } else {
            // If pet not found, clear the UI
           // clearUI();
            Toast.makeText(this, "Pet not found!", Toast.LENGTH_SHORT).show();
        }
    }
    private byte[] convertImageToByteArray(ImageView imageView) {
        // Convert image to byte array (You need to implement this based on your requirements)
        // Example: Convert Bitmap to ByteArray
        Bitmap imageBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    private void clearUI() {
        editTextDescription.setText("");
        editTextAge.setText("");
        editTextSex.setText("");
        editTextNote.setText("");
        petImageView.setImageBitmap(null);
    }
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap selectedImage = BitmapFactory.decodeStream(inputStream);
                petImageView.setImageBitmap(selectedImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}