package com.example.herpath;

public class JobItem {
    private String title;
    private String companyName;
    private String deadline;
    private String description;
    private String location;

    public JobItem(String title, String companyName, String deadline, String description, String location) {
        this.title = title;
        this.companyName = companyName;
        this.deadline = deadline;
        this.description = description;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getDescription(){return description;}

    public String getLocation(){return location;}
}

