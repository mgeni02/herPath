package com.example.herpath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView textViewEmail;
    private Button buttonLogout;
    private EditText editTextTranslate;
    private Button translateButton;
    private Button saveButton;
    private String api = "AIzaSyCothIDIX8h8vY7YNlx7Tk5G3ZYjJGMjXk";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        mAuth = FirebaseAuth.getInstance();
        textViewEmail = findViewById(R.id.textViewEmail);
        editTextTranslate = findViewById(R.id.editTextTranslate);
        translateButton = findViewById(R.id.translateButton);
        buttonLogout = findViewById(R.id.buttonLogout);
        saveButton = findViewById(R.id.saveButton);


        // Retrieve the email from the intent extras
        String email = getIntent().getStringExtra("email");
        textViewEmail.setText(email);

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editTextTranslate.getText().toString())){
                    Toast.makeText(SettingsActivity.this, "Add Text", Toast.LENGTH_SHORT).show();
                }else {// Get the text to translate from the EditText
                // Perform the translation here using the translation API
                // (e.g., using Google Cloud Translation API as shown in the previous responses).

                // After obtaining the translated text, you can display it or use it as needed.
            }
        }});


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingsActivity.this, "Your changes have been saved", Toast.LENGTH_SHORT).show();            }
        });


        TextView textViewHerPath = findViewById(R.id.textViewHerPath);
        textViewHerPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, HomeFeedActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            }
        });

        // Set click listener for the logout button
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        TextView opportunityBoardMenuItem = findViewById(R.id.opportunityBoardMenuItem);
        opportunityBoardMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, OpportunityBoardActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        TextView messagesMenuItem = findViewById(R.id.messagesMenuItem);
        messagesMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SettingsActivity.this, UsersActivity.class);
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
                Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });


        // Add additional menu items setup here
    }

}