package com.example.dog;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class UserDetails extends AppCompatActivity {

    private TextView tvUserName, tvUserEmail, tvUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_details);

        // Initialize the TextViews
        tvUserName = findViewById(R.id.tv_user_name);
        tvUserEmail = findViewById(R.id.tv_user_email);
        tvUserPassword = findViewById(R.id.tv_user_password);

        DBhelper dbHelper = new DBhelper(this);

        // Fetch user details by email
        String email = "user@example.com";
        User user = dbHelper.getUserDetailsByEmail(email);

        if (user != null) {
            // Display user details in the TextViews
            tvUserName.setText(user.getName());
            tvUserEmail.setText(user.getEmail());
            tvUserPassword.setText("******");

            // Log user information for debugging
            String userInfo = "ID: " + user.getId() + "\nName: " + user.getName() + "\nEmail: " + user.getEmail();
            Log.d("User Info", userInfo);
        }
    }
}
