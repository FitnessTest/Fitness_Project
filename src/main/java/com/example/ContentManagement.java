package com.example;

import java.util.*;

public class ContentManagement {

    private Map<String, Content> contentMap;
    private List<String> feedbackList;
    private List<String> complaintList;

    // Constructor initializes the maps and lists
    public ContentManagement() {
        contentMap = new HashMap<>();
        feedbackList = new ArrayList<>();
        complaintList = new ArrayList<>();

        // Sample content
        contentMap.put("Healthy Eating Tips", new Content("Healthy Eating Tips", "article", "Amr Jamhour", "Pending"));
        contentMap.put("Yoga for Beginners", new Content("Yoga for Beginners", "tip", "Ihab Habash", "Approved"));
        contentMap.put("5 Easy Smoothie Recipes", new Content("5 Easy Smoothie Recipes", "recipe", "Ameed Diab", "Rejected"));
    }

    /**
     * Approves or rejects content shared by instructors.
     * Only approves if the content is a health and wellness article or tip.
     */
    public void approveOrRejectContent(String title, boolean approve) {
        Content content = contentMap.get(title);
        if (content != null) {
            if (!isValidHealthAndWellnessContent(content)) {
                System.out.println("Content \"" + title + "\" cannot be approved as it is not a health or wellness article/tip.");
                return;
            }
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

    /**
     * Validates that the content is a health and wellness article or tip.
     */
    private boolean isValidHealthAndWellnessContent(Content content) {
        return "article".equalsIgnoreCase(content.getType()) || "tip".equalsIgnoreCase(content.getType());
    }

    // View all content
    public List<String> viewAllContent() {
        List<String> contentStatuses = new ArrayList<>();
        for (Content content : contentMap.values()) {
            contentStatuses.add(content.getTitle() + " - " + content.getStatus() + " (" + content.getType() + ")");
        }
        return contentStatuses;
    }

    // Handle feedback
    public void handleFeedback(String feedback) {
        feedbackList.add(feedback);
        System.out.println("Feedback added: " + feedback);
    }

    // Handle complaint
    public void handleComplaint(String complaint) {
        complaintList.add(complaint);
        System.out.println("Complaint added: " + complaint);
    }

    // View all feedback
    public List<String> viewAllFeedback() {
        return new ArrayList<>(feedbackList);
    }

    // View all complaints
    public List<String> viewAllComplaints() {
        return new ArrayList<>(complaintList);
    }

    // Content class
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
