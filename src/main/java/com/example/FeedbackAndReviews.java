package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedbackAndReviews {

    public static class ProgramReview {
        private int rating;
        private String reviewText;
        private String improvementSuggestion;


        public ProgramReview(int rating, String reviewText, String improvementSuggestion) {
            if (rating < 1 || rating > 5) {
                throw new IllegalArgumentException("Rating must be between 1 and 5.");
            }
            this.rating = rating;
            this.reviewText = reviewText;
            this.improvementSuggestion = improvementSuggestion;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            if (rating < 1 || rating > 5) {
                throw new IllegalArgumentException("Rating must be between 1 and 5.");
            }
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

    private static Map<String, List<ProgramReview>> programReviews;


    public FeedbackAndReviews() {
        programReviews = new HashMap<>();
    }


    public void submitProgramReview(String programTitle, int rating, String reviewText, String improvementSuggestion) {
        ProgramReview review = new ProgramReview(rating, reviewText, improvementSuggestion);
        programReviews.computeIfAbsent(programTitle, k -> new ArrayList<>()).add(review);
        System.out.println("Review submitted for program: " + programTitle);
    }


    public static void viewProgramReview(String programTitle) {
        List<ProgramReview> reviews = programReviews.get(programTitle);
        if (reviews != null && !reviews.isEmpty()) {
            System.out.println("Reviews for program " + programTitle + ":");
            reviews.forEach(System.out::println);
        } else {
            System.out.println("No reviews available for program: " + programTitle);
        }
    }


    public static void submitImprovementSuggestion(String programTitle, String improvementSuggestion) {
        List<ProgramReview> reviews = programReviews.get(programTitle);
        if (reviews != null && !reviews.isEmpty()) {
            ProgramReview review = reviews.get(reviews.size() - 1); // Update the most recent review
            review.setImprovementSuggestion(improvementSuggestion);
            System.out.println("Improvement suggestion submitted for program: " + programTitle);
        } else {
            System.out.println("No reviews found for program: " + programTitle);
        }
    }
}