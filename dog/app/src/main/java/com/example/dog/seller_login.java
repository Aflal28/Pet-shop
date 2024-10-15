package com.example.dog;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class seller_login extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    Button loginButton;
    TextView signUpText;
    DBhelper dbHelper; // Reference to your database helper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);

        // Initialize database helper
        dbHelper = new DBhelper(this);

        // Initialize views
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signUpText = findViewById(R.id.signupText);

        // Set click listener for login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform login validation
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(seller_login.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                }  else if (!isValidEmail(username)) {
                    Toast.makeText(seller_login.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                } else {
                    // Check user credentials in the database
                    boolean isValidUser = dbHelper.checkUser(username, password);
                    if (isValidUser) {
                        // Login successful
                        Toast.makeText(seller_login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        // Proceed to another activity or perform any action upon successful login
                        // For example, start a new activity
                        Intent intent = new Intent(seller_login.this, Home_seller.class);
                        startActivity(intent);
                    } else {
                        // Login failed
                        Toast.makeText(seller_login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Set click listener for sign-up text
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SignupActivity when SignUp text is clicked
                Intent signupIntent = new Intent(seller_login.this, Register_seller.class);
                startActivity(signupIntent);
            }
        });

    }
    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database connection when the activity is destroyed
        dbHelper.close();
    }
}
