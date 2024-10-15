package com.example.dog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class Add_product extends AppCompatActivity {

    private EditText editTextPetName, editTextDescription, editTextAge, editTextBreed, editTextSex, editTextColor, editTextNote;
    private Spinner spinnerSex;
    private DBhelper dbHelper;
    ImageView petImageView;
    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


        dbHelper = new DBhelper(this);

        editTextPetName = findViewById(R.id.editTextPetName);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextAge = findViewById(R.id.editTextAge);
        spinnerSex = findViewById(R.id.spinnerSex);
        editTextNote = findViewById(R.id.editTextNote);
        petImageView = findViewById(R.id.petImageView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSex.setAdapter(adapter);

        petImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });


    }

    public void onRedirectClicked(View view) {
        String petName = editTextPetName.getText().toString();
        String description = editTextDescription.getText().toString();
        String ageStr = editTextAge.getText().toString().trim();
        String sex = spinnerSex.getSelectedItem().toString();
        String noteStr = editTextNote.getText().toString().trim();

        // Check if any of the fields is empty
        if (TextUtils.isEmpty(petName) || TextUtils.isEmpty(description) || TextUtils.isEmpty(ageStr)
                || TextUtils.isEmpty(sex)  || TextUtils.isEmpty(noteStr)) {
            Toast.makeText(Add_product.this, "Please fill in all product details", Toast.LENGTH_SHORT).show();
            return;
        }

        // Parse integer values
        int age = Integer.parseInt(ageStr);
        int note = Integer.parseInt(noteStr);

        byte[] petImage = convertBitmapToByteArray(((BitmapDrawable) petImageView.getDrawable()).getBitmap());

        long newRowId = dbHelper.addPet(petName, description, age, sex, note, petImage);

        // Check if insertion was successful

        if (newRowId != -1) {
            Toast.makeText(Add_product.this, "Products Added Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Add_product.this, "Products Added Failed", Toast.LENGTH_SHORT).show();
        }

    }
    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
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
