package com.example.herpath;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OpportunityBoardAdapter extends RecyclerView.Adapter<OpportunityBoardAdapter.OpportunityViewHolder> {
    private List<JobItem> opportunityItemList;
    String email;

    public OpportunityBoardAdapter(List<JobItem> opportunityItemList) {
        this.opportunityItemList = opportunityItemList;
    }

    @NonNull
    @Override
    public OpportunityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_opportunity, parent, false);
        return new OpportunityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OpportunityViewHolder holder, int position) {
        JobItem item = opportunityItemList.get(position);
        holder.bind(item,email);
    }

    @Override
    public int getItemCount() {
        return opportunityItemList.size();
    }

    public static class OpportunityViewHolder extends RecyclerView.ViewHolder {
        private TextView opportunityTitleTextView;
        private TextView companyTextView;
        private TextView deadlineTextView;
        private TextView locationTextView;
        private Button applyButton;

        public OpportunityViewHolder(@NonNull View itemView) {
            super(itemView);
            opportunityTitleTextView = itemView.findViewById(R.id.jobTitleTextView);
            companyTextView = itemView.findViewById(R.id.companyNameTextView);
            deadlineTextView = itemView.findViewById(R.id.deadlineTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            applyButton = itemView.findViewById(R.id.applyButton);
        }

        public void bind(JobItem item, String email) {
            opportunityTitleTextView.setText(item.getTitle());
            companyTextView.setText(item.getCompanyName());
            deadlineTextView.setText(item.getDeadline());
            locationTextView.setText(item.getLocation());

            // Set click listener for apply button
            applyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Handle apply button click event
                    // Launch JobDetailsActivity and pass job details and email
                    Intent intent = new Intent(itemView.getContext(), JobDetailsActivity.class);
                    intent.putExtra("jobTitle", item.getTitle());
                    intent.putExtra("companyName", item.getCompanyName());
                    intent.putExtra("deadline", item.getDeadline());
                    intent.putExtra("location", item.getLocation());
                    intent.putExtra("description", item.getDescription());

                    intent.putExtra("email", email);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

}


