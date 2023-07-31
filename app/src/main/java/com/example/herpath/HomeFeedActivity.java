package com.example.herpath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HomeFeedActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HomeFeedAdapter homeFeedAdapter;
    private FirebaseAuth mAuth;
    private TextView textViewEmail;
    private Button buttonLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



        mAuth = FirebaseAuth.getInstance();
        textViewEmail = findViewById(R.id.textViewEmail);
        buttonLogout = findViewById(R.id.buttonLogout);

        // Retrieve the email from the intent extras
        String email = getIntent().getStringExtra("email");
        textViewEmail.setText(email);

        // Initialize the recyclerview
        recyclerView = findViewById(R.id.recyclerViewHomeFeed);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create a list of home feed items
        List<HomeFeedItem> homeFeedItemList = new ArrayList<>();
        // Create sample home feed items
        HomeFeedItem item1 = new HomeFeedItem("John Doe", "9:30 AM", "This is the first post", "12 likes", "43 comments" );
        HomeFeedItem item2 = new HomeFeedItem("Jane Smith", "10:00 AM", "This is the second post","23 likes", "90 comments");
        HomeFeedItem item3 = new HomeFeedItem("Alice Johnson", "11:15 AM", "This is the third post", "50 likes", "100 comments");

        // Add the items to the list
        homeFeedItemList.add(item1);
        homeFeedItemList.add(item2);
        homeFeedItemList.add(item3);

        // Create the Adapter and send it to the RecyclerView
        homeFeedAdapter = new HomeFeedAdapter(homeFeedItemList);
        recyclerView.setAdapter(homeFeedAdapter);

        TextView textViewHerPath = findViewById(R.id.textViewHerPath);
        textViewHerPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeFeedActivity.this, HomeFeedActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            }
        });
        // Find the FloatingActionButton by its ID
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);

        // Set an OnClickListener to handle clicks on the FAB
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeFeedActivity.this, AddPost.class);
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
                Intent intent = new Intent(HomeFeedActivity.this, OpportunityBoardActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
        // Add click listeners for other menu items if needed
        // Inside onCreate() method after the click listener for logout button
        TextView messagesMenuItem = findViewById(R.id.messagesMenuItem);
        messagesMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeFeedActivity.this, UsersActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        TextView settingsMenuItem = findViewById(R.id.settingsMenuItem);
        settingsMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle settings menu item click event
                // Launch SettingsActivity
                Intent intent = new Intent(HomeFeedActivity.this, SettingsActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });


        // Add additional menu items setup here
    }

}
