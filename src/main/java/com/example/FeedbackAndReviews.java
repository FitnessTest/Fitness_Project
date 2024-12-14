package com.example;

import java.util.HashMap;
import java.util.Map;

public class FeedbackAndReviews {

    public static class ProgramReview {
        private int rating; // Rating out of 5
        private String reviewText; // Review comments
        private String improvementSuggestion; // Improvement suggestions for the program


        public ProgramReview(int rating, String reviewText, String improvementSuggestion) {
            this.rating = rating;
            this.reviewText = reviewText;
            this.improvementSuggestion = improvementSuggestion;
        }


        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getReviewText() {
            return reviewText;
        }

        public void setReviewText(String reviewText) {
            this.reviewText = reviewText;
        }

        public String getImprovementSuggestion() {
            return improvementSuggestion;
        }

        public void setImprovementSuggestion(String improvementSuggestion) {
            this.improvementSuggestion = improvementSuggestion;
        }

        @Override
        public String toString() {
            return "Rating: " + rating + "/5\nReview: " + reviewText + "\nImprovement Suggestion: " + improvementSuggestion;
        }
    }


    private Map<String, ProgramReview> programReviews;


    public FeedbackAndReviews() {
        programReviews = new HashMap<>();
    }


    public void submitProgramReview(String programTitle, int rating, String reviewText, String improvementSuggestion) {
        ProgramReview review = new ProgramReview(rating, reviewText, improvementSuggestion);
        programReviews.put(programTitle, review);
        System.out.println("Review submitted for program: " + programTitle);
    }

    public void viewProgramReview(String programTitle) {
        ProgramReview review = programReviews.get(programTitle);
        if (review != null) {
            System.out.println("Review for program " + programTitle + ":\n" + review);
        } else {
            System.out.println("No reviews available for program: " + programTitle);
        }
    }

    public void submitImprovementSuggestion(String programTitle, String improvementSuggestion) {
        ProgramReview review = programReviews.get(programTitle);
        if (review != null) {
            review.setImprovementSuggestion(improvementSuggestion);
            System.out.println("Improvement suggestion submitted for program: " + programTitle);
        } else {
            System.out.println("No reviews found for program: " + programTitle);
        }
    }

    public static void main(String[] args) {
        FeedbackAndReviews feedbackSystem = new FeedbackAndReviews();


        feedbackSystem.submitProgramReview("Weight Loss Challenge", 5, "Great program, very effective!", "Include more variety in exercises.");


        feedbackSystem.viewProgramReview("Weight Loss Challenge");

        feedbackSystem.submitImprovementSuggestion("Weight Loss Challenge", "Add more meal prep tips for beginners.");

        feedbackSystem.viewProgramReview("Weight Loss Challenge");


        feedbackSystem.viewProgramReview("Muscle Building Mastery");
    }
}
