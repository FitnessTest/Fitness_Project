package com.example;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContentManagement {

    private static final Logger LOGGER = Logger.getLogger(ContentManagement.class.getName());

    private Map<String, Content> contentMap;
    private List<String> feedbackList;
    private List<String> complaintList;

    public ContentManagement() {
        contentMap = new HashMap<>();
        feedbackList = new ArrayList<>();
        complaintList = new ArrayList<>();


        contentMap.put("Healthy Eating Tips", new Content("Healthy Eating Tips", "article", "Amr Jamhour", "Pending"));
        contentMap.put("Yoga for Beginners", new Content("Yoga for Beginners", "tip", "Ihab Habash", "Approved"));
        contentMap.put("5 Easy Smoothie Recipes", new Content("5 Easy Smoothie Recipes", "recipe", "Ameed Diab", "Rejected"));
    }

    public void approveOrRejectContent(String title, boolean approve) {
        Content content = contentMap.get(title);
        if (content != null) {
            if (!isValidHealthAndWellnessContent(content)) {
                LOGGER.log(Level.WARNING, "Content \"{0}\" cannot be approved as it is not a health or wellness article/tip.", title);
                return;
            }
            if (approve) {
                content.setStatus("Approved");
                LOGGER.log(Level.INFO, "Content \"{0}\" has been approved.", title);
            } else {
                content.setStatus("Rejected");
                LOGGER.log(Level.INFO, "Content \"{0}\" has been rejected.", title);
            }
        } else {
            LOGGER.log(Level.WARNING, "Content not found: {0}", title);
        }
    }

    private boolean isValidHealthAndWellnessContent(Content content) {
        return "article".equalsIgnoreCase(content.getType()) || "tip".equalsIgnoreCase(content.getType());
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
        LOGGER.log(Level.INFO, "Feedback added: {0}", feedback);
    }

    public void handleComplaint(String complaint) {
        complaintList.add(complaint);
        LOGGER.log(Level.INFO, "Complaint added: {0}", complaint);
    }

    public List<String> viewAllFeedback() {
        return new ArrayList<>(feedbackList);
    }

    public List<String> viewAllComplaints() {
        return new ArrayList<>(complaintList);
    }

    // Inner Content class
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
