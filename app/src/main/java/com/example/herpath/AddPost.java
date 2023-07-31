package com.example.herpath;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddPost extends AppCompatActivity {

    private EditText editTextCaption;
    private Button buttonUploadImage;
    private Button btnAddPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
// Find views by their IDs
        editTextCaption = findViewById(R.id.editTextCaption);
        buttonUploadImage = findViewById(R.id.buttonUploadImage);
        btnAddPost = findViewById(R.id.addPost);

        // Set click listener for the "Upload Image" button
        buttonUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement the image upload functionality here.
                // You can open an image picker or camera intent to choose/capture an image.
                // The code for image upload will depend on your specific implementation.
            }
        });

        btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity to go back to the previous activity in the back stack (MessagesActivity)
                finish();
            }
        });
    }

    }


