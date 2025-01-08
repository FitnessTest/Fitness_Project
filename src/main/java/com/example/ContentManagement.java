package com.example;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class manages content related to health and wellness, including handling
 * the approval or rejection of content, managing feedback and complaints, and
 * viewing the status of content.
 */
public class ContentManagement {

    private static final Logger LOGGER = Logger.getLogger(ContentManagement.class.getName());

    private Map<String, Content> contentMap;
    private List<String> feedbackList;
    private List<String> complaintList;

    /**
     * Constructor that initializes the content management system with some
     * default content and empty feedback and complaint lists.
     */
    public ContentManagement() {
        contentMap = new HashMap<>();
        feedbackList = new ArrayList<>();
        complaintList = new ArrayList<>();

        // Adding some default content
        contentMap.put("Healthy Eating Tips", new Content("Healthy Eating Tips", "article", "Amr Jamhour", "Pending"));
        contentMap.put("Yoga for Beginners", new Content("Yoga for Beginners", "tip", "Ihab Habash", "Approved"));
        contentMap.put("5 Easy Smoothie Recipes", new Content("5 Easy Smoothie Recipes", "recipe", "Ameed Diab", "Rejected"));
    }

    /**
     * Approves or rejects content based on the provided title and approval flag.
     *
     * @param title The title of the content.
     * @param approve A boolean indicating whether to approve (true) or reject (false) the content.
     */
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

    /**
     * Validates whether the content is of type 'article' or 'tip'.
     *
     * @param content The content to validate.
     * @return true if the content is a valid health-related article or tip, false otherwise.
     */
    private boolean isValidHealthAndWellnessContent(Content content) {
        return "article".equalsIgnoreCase(content.getType()) || "tip".equalsIgnoreCase(content.getType());
    }

    /**
     * Returns a list of all content with their statuses.
     *
     * @return A list of strings representing content titles, their statuses, and types.
     */
    public List<String> viewAllContent() {
        List<String> contentStatuses = new ArrayList<>();
        for (Content content : contentMap.values()) {
            contentStatuses.add(content.getTitle() + " - " + content.getStatus() + " (" + content.getType() + ")");
        }
        return contentStatuses;
    }

    /**
     * Adds feedback to the feedback list.
     *
     * @param feedback The feedback message to be added.
     */
    public void handleFeedback(String feedback) {
        feedbackList.add(feedback);
        LOGGER.log(Level.INFO, "Feedback added: {0}", feedback);
    }

    /**
     * Adds a complaint to the complaint list.
     *
     * @param complaint The complaint message to be added.
     */
    public void handleComplaint(String complaint) {
        complaintList.add(complaint);
        LOGGER.log(Level.INFO, "Complaint added: {0}", complaint);
    }

    /**
     * Returns a list of all feedback messages.
     *
     * @return A list of feedback messages.
     */
    public List<String> viewAllFeedback() {
        return new ArrayList<>(feedbackList);
    }

    /**
     * Returns a list of all complaint messages.
     *
     * @return A list of complaint messages.
     */
    public List<String> viewAllComplaints() {
        return new ArrayList<>(complaintList);
    }

    /**
     * Inner class representing the content being managed, with its title, type,
     * instructor name, and status.
     */
    class Content {
        private String title;
        private String type; // article, tip, or recipe
        private String instructorName;
        private String status; // Pending, Approved, or Rejected

        /**
         * Constructor for the Content class.
         *
         * @param title The title of the content.
         * @param type The type of content (article, tip, or recipe).
         * @param instructorName The name of the instructor.
         * @param status The status of the content (Pending, Approved, or Rejected).
         */
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
