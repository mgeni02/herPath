package com.example.herpath;

public class HomeFeedItem {
    private String displayName;
    private String postTime;
    private String description;
    private String like_count;
    private String comment_count;

    public HomeFeedItem(String displayName, String postTime, String description, String like_count, String comment_count) {
        this.displayName = displayName;
        this.postTime = postTime;
        this.description = description;
        this.like_count = like_count;
        this.comment_count=comment_count;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPostTime() {
        return postTime;
    }

    public String getDescription() {
        return description;
    }

    public String getLike_count(){return like_count;}
    public String getComment_count(){return comment_count;}
}

