package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class buyer_register extends AppCompatActivity {

    EditText nameEditText, emailEditText, passwordEditText;
    Button signupButton;
    TextView loginText;

    private DBhelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_register);

        dbHelper = new DBhelper(this);

        nameEditText = findViewById(R.id.user1);
        emailEditText = findViewById(R.id.sign_username1);
        passwordEditText = findViewById(R.id.sign_password1);
        signupButton = findViewById(R.id.signup_Button1);
        loginText = findViewById(R.id.Login_Text);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(buyer_register.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(buyer_register.this, " \"Enter a valid email address (e.g., example@example.com)\"", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if the password meets the criteria
                    if (!isValidPassword(password)) {
                        Toast.makeText(buyer_register.this, "Password should contain at least 8 characters including uppercase, lowercase, symbols, and numbers", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Add user to the database
                    dbHelper.addCaretaker(name, email, password);

                    // Show success message and finish this activity
                    Toast.makeText(buyer_register.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to login activity when the "Login" text is clicked
                startActivity(new Intent(buyer_register.this, buyer_login.class));
                // Replace LoginActivity.class with your actual login activity
            }
        });

    }
    private boolean isValidPassword(String password) {
        // Password should contain at least 8 characters including uppercase, lowercase, symbols, and numbers
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[!@#$%^&*()_+\\-={};':\"<>?,./\\\\].*");
    }
    private boolean isValidEmail(CharSequence target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
