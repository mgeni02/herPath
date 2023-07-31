package com.example.herpath;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeFeedAdapter extends RecyclerView.Adapter<HomeFeedAdapter.HomeFeedViewHolder> {
    private List<HomeFeedItem> homeFeedItemList;

    public HomeFeedAdapter(List<HomeFeedItem> homeFeedItemList) {
        this.homeFeedItemList = homeFeedItemList;
    }

    @NonNull
    @Override
    public HomeFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_feed, parent, false);
        return new HomeFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFeedViewHolder holder, int position) {
        HomeFeedItem item = homeFeedItemList.get(position);
        // Bind data to views in the view holder
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return homeFeedItemList.size();
    }

    public static class HomeFeedViewHolder extends RecyclerView.ViewHolder {
        // Declare views in the view holder
        private TextView displayNameTextView;
        private TextView postTimeTextView;
        private TextView descriptionTextView;
        private TextView like_count;
        private TextView comment_count;

        public HomeFeedViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views in the view holder
            displayNameTextView = itemView.findViewById(R.id.display_name_text_view);
            postTimeTextView = itemView.findViewById(R.id.post_time_text_view);
            descriptionTextView = itemView.findViewById(R.id.description_textview);
            like_count = itemView.findViewById(R.id.like_count_textview);
            comment_count = itemView.findViewById(R.id.comment_count_textview);
        }

        public void bind(HomeFeedItem item) {
            // Set data to views in the view holder
            displayNameTextView.setText(item.getDisplayName());
            postTimeTextView.setText(item.getPostTime());
            descriptionTextView.setText(item.getDescription());
            like_count.setText(item.getLike_count());
            comment_count.setText(item.getComment_count());
        }
    }
}
