package com.example;


import java.util.*;

public class ContentManagement {

    private Map<String, Content> contentMap;
    private List<String> feedbackList;
    private List<String> complaintList;


    public ContentManagement() {
        contentMap = new HashMap<>();
        feedbackList = new ArrayList<>();
        complaintList = new ArrayList<>();


        contentMap.put("Healthy Eating Tips", new Content("Healthy Eating Tips", "article", "John Doe", "Pending"));
        contentMap.put("Yoga for Beginners", new Content("Yoga for Beginners", "tip", "Jane Smith", "Approved"));
        contentMap.put("5 Easy Smoothie Recipes", new Content("5 Easy Smoothie Recipes", "recipe", "Alex Johnson", "Rejected"));
    }


    public void approveOrRejectContent(String title, boolean approve) {
        Content content = contentMap.get(title);
        if (content != null) {
            if (approve) {
                content.setStatus("Approved");
                System.out.println("Content \"" + title + "\" has been approved.");
            } else {
                content.setStatus("Rejected");
                System.out.println("Content \"" + title + "\" has been rejected.");
            }
        } else {
            System.out.println("Content not found: " + title);
        }
    }


    public List<String> viewAllContent() {
        List<String> contentStatuses = new ArrayList<>();
        for (Content content : contentMap.values()) {
            contentStatuses.add(content.getTitle() + " - " + content.getStatus() + " (" + content.getType() + ")");
        }
        return contentStatuses;
    }


    public void handleFeedback(String feedback) {
        feedbackList.add(feedback);
        System.out.println("Feedback added: " + feedback);
    }


    public void handleComplaint(String complaint) {
        complaintList.add(complaint);
        System.out.println("Complaint added: " + complaint);
    }


    public List<String> viewAllFeedback() {
        return new ArrayList<>(feedbackList);
    }


    public List<String> viewAllComplaints() {
        return new ArrayList<>(complaintList);
    }


    class Content {
        private String title;
        private String type; // article, tip, or recipe
        private String instructorName;
        private String status; // Pending, Approved, or Rejected

        public Content(String title, String type, String instructorName, String status) {
            this.title = title;
            this.type = type;
            this.instructorName = instructorName;
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }

        public String getInstructorName() {
            return instructorName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

}
