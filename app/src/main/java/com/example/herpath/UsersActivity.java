package com.example.herpath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUsers;
    private UsersAdapter usersAdapter;
    private List<User> userList = new ArrayList<>(); // Initialize as empty ArrayList
    private List<User> originalUserList = new ArrayList<>(); // Store the original user list fetched from Firebase


    private FirebaseAuth mAuth;
    private TextView textViewEmail;
    private Button buttonLogout;
    private EditText editTextSearch;
    private Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        mAuth = FirebaseAuth.getInstance();
        textViewEmail = findViewById(R.id.textViewEmail);
        buttonLogout = findViewById(R.id.buttonLogout);

        // Retrieve the email from the intent extras
        String email = getIntent().getStringExtra("email");
        textViewEmail.setText(email);

        // Initialize RecyclerView and its adapter
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));
        usersAdapter = new UsersAdapter(userList);
        recyclerViewUsers.setAdapter(usersAdapter);

        // Fetch users from Firebase and display them in the RecyclerView
        fetchUsersFromFirebase();



        // Set click listener for RecyclerView items
        usersAdapter.setOnItemClickListener(new UsersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position >= 0 && position < userList.size()) {
                    User clickedUser = userList.get(position);
                    if (clickedUser != null && clickedUser.getUserId() != null) {
                        openConversationActivity(clickedUser.getUsername(), clickedUser.getUserId());
                    } else {
                        if (clickedUser == null) {
                            Log.d("UsersActivity", "Clicked user is null.");
                        } else {
                            Log.d("UsersActivity", "Invalid user ID: " + clickedUser.getUserId());
                        }
                        Toast.makeText(UsersActivity.this, "Something is off", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Initialize search components
        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);

        // Set click listener for search button
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });

        // Set text change listener for search EditText
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                performSearch();
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
    }

    // Fetch users from Firebase and display them in the RecyclerView
    private void fetchUsersFromFirebase() {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear(); // Clear the existing userList before adding new data
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    if (user != null) {
                        userList.add(user);
                    } else {
                        Log.d("UsersActivity", "Invalid user data: " + userSnapshot);
                    }
                }
                Log.d("UsersActivity", "Fetched users count: " + userList.size());

                // Store the original user list fetched from Firebase
                //originalUserList.clear();
                //originalUserList.addAll(userList);

                // Update the data in the adapter
                usersAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }


    // Implement search functionality here if needed
    // Perform search based on the text in the search EditText
    private void performSearch() {
        String searchQuery = editTextSearch.getText().toString().trim().toLowerCase();

        if (searchQuery.isEmpty()) {
            // If the search query is empty, show the original user list
            usersAdapter.updateData(originalUserList);
        } else {
            // If the search query is not empty, filter the user list based on the query
            List<User> filteredList = new ArrayList<>();
            for (User user : originalUserList) {
                // Filter based on username or other relevant user attributes
                if (user.getUsername().toLowerCase().contains(searchQuery)) {
                    filteredList.add(user);
                }
            }
            usersAdapter.updateData(filteredList);
        }
    }

    private void openConversationActivity(String username, String userId) {
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(userId)) {
            Intent intent = new Intent(this, ConversationActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("userId", userId);
            startActivity(intent);
        } else {
            // Handle the case where username or userId is null or empty
            Toast.makeText(this, "Invalid user", Toast.LENGTH_SHORT).show();
        }
    }

}
