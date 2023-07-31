package com.example.herpath;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConversationActivity extends AppCompatActivity {

    private RecyclerView recyclerViewConversation;
    private ConversationAdapter conversationAdapter;
    private List<Message> conversationMessages;
    private TextView sender;
    private EditText editTextMessage;
    private Button buttonSend;

    private FirebaseAuth mAuth;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();

        // Retrieve the sender's name and ID from the intent extras
        String senderName = getIntent().getStringExtra("username");
        String senderId = getIntent().getStringExtra("userId");

        sender = findViewById(R.id.titleTextView);
        sender.setText(senderName);

        // Initialize message input components
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);

        // Set click listener for send button
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageContent = editTextMessage.getText().toString().trim();
                if (!TextUtils.isEmpty(messageContent)) {
                    sendMessage(senderId, messageContent);
                    editTextMessage.setText(""); // Clear the message input after sending
                }
            }
        });

        // Initialize RecyclerView and its adapter
        recyclerViewConversation = findViewById(R.id.recyclerViewConversation);
        recyclerViewConversation.setLayoutManager(new LinearLayoutManager(this));
        conversationMessages = new ArrayList<>(); // Initialize an empty list for conversation messages
        conversationAdapter = new ConversationAdapter(conversationMessages);

        recyclerViewConversation.setAdapter(conversationAdapter);

        // Fetch conversation messages from Firebase
        fetchConversationMessages(senderId);

        // Handle back button click
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity to go back to the previous activity in the back stack (MessagesActivity)
                finish();
            }
        });
    }

    private void sendMessage(String receiverId, String messageContent) {
        DatabaseReference messagesRef = FirebaseDatabase.getInstance().getReference("messages");
        DatabaseReference senderConversationRef = messagesRef.child(currentUserId).child(receiverId).push();
        DatabaseReference receiverConversationRef = messagesRef.child(receiverId).child(currentUserId).push();

        String messageId = senderConversationRef.getKey();

        long timestamp = System.currentTimeMillis();

        Message message = new Message(messageId, currentUserId, receiverId, messageContent, timestamp);

        senderConversationRef.setValue(message);
        receiverConversationRef.setValue(message);
    }

    private void fetchConversationMessages(String senderId) {
        DatabaseReference conversationRef = FirebaseDatabase.getInstance().getReference("messages").child(currentUserId).child(senderId);
        conversationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                conversationMessages.clear(); // Clear the existing conversationMessages before adding new data
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    Message message = messageSnapshot.getValue(Message.class);
                    conversationMessages.add(message);
                }
                conversationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }

}
