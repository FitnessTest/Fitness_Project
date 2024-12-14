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

    public static void main(String[] args) {
        ContentManagement cm = new ContentManagement();


        System.out.println("All Content:");
        for (String contentStatus : cm.viewAllContent()) {
            System.out.println(contentStatus);
        }


        cm.approveOrRejectContent("Healthy Eating Tips", true);
        cm.approveOrRejectContent("5 Easy Smoothie Recipes", false);


        System.out.println("\nUpdated Content Statuses:");
        for (String contentStatus : cm.viewAllContent()) {
            System.out.println(contentStatus);
        }


        cm.handleFeedback("Great article on healthy eating!");
        cm.handleFeedback("Yoga tips were very helpful!");


        cm.handleComplaint("The smoothie recipe wasn't clear.");
        cm.handleComplaint("Some health tips were outdated.");


        System.out.println("\nUser Feedback:");
        for (String feedback : cm.viewAllFeedback()) {
            System.out.println(feedback);
        }

        System.out.println("\nUser Complaints:");
        for (String complaint : cm.viewAllComplaints()) {
            System.out.println(complaint);
        }
    }
}
