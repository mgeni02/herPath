package com.example.herpath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class JobDetailsActivity extends AppCompatActivity {

    private TextView jobTitleTextView;
    private TextView companyNameTextView;
    private TextView deadlineTextView;
    private TextView locationTextView;
    private TextView jobDescriptionTextView;
    private TextView textViewEmail;

    private FirebaseAuth mAuth;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opportunity_detail);

        mAuth = FirebaseAuth.getInstance();
        textViewEmail = findViewById(R.id.textViewEmail);
        buttonLogout = findViewById(R.id.buttonLogout);

        // Retrieve the email from the intent extras
        String email = getIntent().getStringExtra("email");
        textViewEmail.setText(email);

        jobTitleTextView = findViewById(R.id.jobTitleTextView);
        companyNameTextView = findViewById(R.id.companyNameTextView);
        deadlineTextView = findViewById(R.id.deadlineTextView1);
        locationTextView = findViewById(R.id.locationTextView);
        jobDescriptionTextView = findViewById(R.id.jobDescriptionTextView);

        // Retrieve the job details from the intent extras
        String jobTitle = getIntent().getStringExtra("jobTitle");
        String companyName = getIntent().getStringExtra("companyName");
        String deadline = getIntent().getStringExtra("deadline");
        String location = getIntent().getStringExtra("location");
        String jobDescription = getIntent().getStringExtra("description");

        // Set the job details to the views
        jobTitleTextView.setText(jobTitle);
        companyNameTextView.setText(companyName);
        deadlineTextView.setText(deadline);
        locationTextView.setText(location);
        jobDescriptionTextView.setText(jobDescription);

        TextView textViewHerPath = findViewById(R.id.textViewHerPath);
        textViewHerPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetailsActivity.this, HomeFeedActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            }
        });

        {
            TextView opportunityBoardMenuItem = findViewById(R.id.opportunityBoardMenuItem);
            opportunityBoardMenuItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(JobDetailsActivity.this, OpportunityBoardActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }
            });

            TextView messagesMenuItem = findViewById(R.id.messagesMenuItem);
            messagesMenuItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(JobDetailsActivity.this, UsersActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }
            });


            // Add click listeners for other menu items if needed
            // Inside onCreate() method after the click listener for logout button
            TextView settingsMenuItem = findViewById(R.id.settingsMenuItem);
            settingsMenuItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Handle settings menu item click event
                    // Launch SettingsActivity
                    Intent intent = new Intent(JobDetailsActivity.this, SettingsActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }
            });


            buttonLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mAuth.signOut();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });


        }

    }
}