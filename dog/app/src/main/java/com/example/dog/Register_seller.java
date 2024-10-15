package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register_seller extends AppCompatActivity {

    EditText nameEditText, emailEditText, passwordEditText;
    Button signupButton;
    TextView Logintext;
    DBhelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_seller);

        // Initialize database helper
        dbHelper = new DBhelper(this);

        nameEditText = findViewById(R.id.user);
        emailEditText = findViewById(R.id.sign_username);
        passwordEditText = findViewById(R.id.sign_password);
        signupButton = findViewById(R.id.signup_Button);
        Logintext=findViewById(R.id.Login_Text);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user input
                String name = nameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validate input fields
                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Register_seller.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }  else if (!isValidEmail(email)) {
                    Toast.makeText(Register_seller.this, " \"Enter a valid email address (e.g., example@example.com)\"", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if the password meets the criteria
                    if (!isValidPassword(password)) {
                        Toast.makeText(Register_seller.this, "Password should contain at least 8 characters including uppercase, lowercase, symbols, and numbers", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Add user to the database
                    dbHelper.addUser(name, email, password);

                    // Show success message and finish this activity
                    Toast.makeText(Register_seller.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        // ... (other click listeners and methods)
        Logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SignupActivity when SignUp text is clicked
                Intent signupIntent = new Intent(Register_seller.this, seller_login.class);
                startActivity(signupIntent);
            }
        });

    }
    protected void onDestroy() {
        super.onDestroy();
        // Close the database connection when the activity is destroyed
        dbHelper.close();
    }


    // ... (onDestroy and other methods)

    // Password validation function
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



