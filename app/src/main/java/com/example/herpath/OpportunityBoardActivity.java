package com.example.herpath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class OpportunityBoardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OpportunityBoardAdapter opportunityBoardAdapter;
    private FirebaseAuth mAuth;
    private TextView textViewEmail;
    private EditText searchEditText;
    private Button searchButton;

    private Button buttonLogout;

    private List<JobItem> jobItemList; // List of job items
    private List<JobItem> filteredJobItemList; // List to hold filtered job items


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opportunity_board);



        mAuth = FirebaseAuth.getInstance();
        textViewEmail = findViewById(R.id.textViewEmail);
        buttonLogout = findViewById(R.id.buttonLogout);

        // Retrieve the email from the intent extras
        String email = getIntent().getStringExtra("email");
        textViewEmail.setText(email);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerViewOpportunityBoard);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create a list of job items
        jobItemList = new ArrayList<>();
        filteredJobItemList = new ArrayList<>();
        // Add sample job items
        JobItem jobItem1 = new JobItem("Job Title 1", "Company Name 1", "Deadline 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus fringilla libero augue, sit amet scelerisque neque lobortis et. Aenean vel consequat urna, in tempus nibh. Donec sodales eleifend ipsum, sit amet maximus ex finibus a. Mauris vel massa condimentum, tincidunt enim vel, tempor nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; In molestie convallis nulla sed condimentum. Praesent porttitor sapien vel tincidunt rutrum.","Grand Baie, Mauritius");
        JobItem jobItem2 = new JobItem("Job Title 2", "Company Name 2", "Deadline 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus fringilla libero augue, sit amet scelerisque neque lobortis et. Aenean vel consequat urna, in tempus nibh. Donec sodales eleifend ipsum, sit amet maximus ex finibus a. Mauris vel massa condimentum, tincidunt enim vel, tempor nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; In molestie convallis nulla sed condimentum. Praesent porttitor sapien vel tincidunt rutrum.","Kigali,Rwanda");
        JobItem jobItem3 = new JobItem("Job Title 3", "Company Name 3", "Deadline 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus fringilla libero augue, sit amet scelerisque neque lobortis et. Aenean vel consequat urna, in tempus nibh. Donec sodales eleifend ipsum, sit amet maximus ex finibus a. Mauris vel massa condimentum, tincidunt enim vel, tempor nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; In molestie convallis nulla sed condimentum. Praesent porttitor sapien vel tincidunt rutrum.","Nairabi, Kenya");
        // Add the job items to the list
        jobItemList.add(jobItem1);
        jobItemList.add(jobItem2);
        jobItemList.add(jobItem3);

        // Initially, display all job items
        filteredJobItemList.addAll(jobItemList);

        // Find views for search bar elements
        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);

        // Create the adapter and set it to the RecyclerView
        opportunityBoardAdapter = new OpportunityBoardAdapter(filteredJobItemList);
        recyclerView.setAdapter(opportunityBoardAdapter);

        TextView textViewHerPath = findViewById(R.id.textViewHerPath);
        textViewHerPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpportunityBoardActivity.this, HomeFeedActivity.class);
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

        // Set a TextWatcher to filter job items based on the location entered in the search bar
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterJobItems(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // Set a click listener for the search button to perform filtering
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchQuery = searchEditText.getText().toString();
                filterJobItems(searchQuery);
            }
        });

        TextView opportunityBoardMenuItem = findViewById(R.id.opportunityBoardMenuItem);
        opportunityBoardMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpportunityBoardActivity.this, OpportunityBoardActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        TextView messagesMenuItem = findViewById(R.id.messagesMenuItem);
        messagesMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(OpportunityBoardActivity.this, UsersActivity.class);
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
                Intent intent = new Intent(OpportunityBoardActivity.this, SettingsActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });


        // Add additional menu items setup here
    }

    private void filterJobItems(String locationQuery) {
        filteredJobItemList.clear();

        if (locationQuery.isEmpty()) {
            filteredJobItemList.addAll(jobItemList);
        } else {
            for (JobItem jobItem : jobItemList) {
                if (jobItem.getLocation().toLowerCase().contains(locationQuery.toLowerCase())) {
                    filteredJobItemList.add(jobItem);
                }
            }
        }
        // Notify the adapter that the data has changed
        opportunityBoardAdapter.notifyDataSetChanged();
    }

}

