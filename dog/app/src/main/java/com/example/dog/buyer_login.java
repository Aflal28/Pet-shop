package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class buyer_login extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    Button loginButton;
    TextView signupText;

    private DBhelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_login);

        dbHelper = new DBhelper(this);

        usernameEditText = findViewById(R.id.user_name);
        passwordEditText = findViewById(R.id.Password);
        loginButton = findViewById(R.id.login_Button);
        signupText = findViewById(R.id.signup_Text);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(buyer_login.this, "Please fill both fields", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(username)) {
                    Toast.makeText(buyer_login.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                } else {
                    // Check user credentials in the database
                    boolean isValidUser = dbHelper.checkCaretaker(username, password);
                    if (isValidUser) {
                        // Login successful
                        Toast.makeText(buyer_login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        // Proceed to another activity or perform any action upon successful login
                        // For example, start a new activity
                        Intent intent = new Intent(buyer_login.this, buyer_home.class);
                        startActivity(intent);
                    } else {
                        // Login failed
                        Toast.makeText(buyer_login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to signup activity when the "SignUp Now" text is clicked
                startActivity(new Intent(buyer_login.this, buyer_register.class));
                // Replace SignupActivity.class with your actual signup activity
            }
        });

    }
    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
