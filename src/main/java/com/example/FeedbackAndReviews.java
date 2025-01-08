package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class handles the management of feedback and reviews for different programs.
 * It allows submission, viewing, and updating reviews and suggestions.
 */
public class FeedbackAndReviews {
    private static final Logger LOGGER = Logger.getLogger(FeedbackAndReviews.class.getName());

    /**
     * The ProgramReview class represents a single review for a program.
     * It contains a rating, review text, and improvement suggestions.
     */
    public static class ProgramReview {
        private int rating;
        private String reviewText;
        private String improvementSuggestion;

        /**
         * Constructs a ProgramReview with the specified rating, review text, and improvement suggestion.
         * @param rating the rating for the program (between 1 and 5)
         * @param reviewText the review text for the program
         * @param improvementSuggestion the improvement suggestion for the program
         * @throws IllegalArgumentException if the rating is not between 1 and 5
         */
        public ProgramReview(int rating, String reviewText, String improvementSuggestion) {
            if (rating < 1 || rating > 5) {
                throw new IllegalArgumentException("Rating must be between 1 and 5.");
            }
            this.rating = rating;
            this.reviewText = reviewText;
            this.improvementSuggestion = improvementSuggestion;
        }

        /**
         * Gets the rating of the program review.
         * @return the rating of the program
         */
        public int getRating() {
            return rating;
        }

        /**
         * Sets the rating of the program review.
         * @param rating the new rating (between 1 and 5)
         * @throws IllegalArgumentException if the rating is not between 1 and 5
         */
        public void setRating(int rating) {
            if (rating < 1 || rating > 5) {
                throw new IllegalArgumentException("Rating must be between 1 and 5.");
            }
            this.rating = rating;
        }

        /**
         * Gets the review text for the program.
         * @return the review text
         */
        public String getReviewText() {
            return reviewText;
        }

        /**
         * Sets the review text for the program.
         * @param reviewText the new review text
         */
        public void setReviewText(String reviewText) {
            this.reviewText = reviewText;
        }

        /**
         * Gets the improvement suggestion for the program.
         * @return the improvement suggestion
         */
        public String getImprovementSuggestion() {
            return improvementSuggestion;
        }

        /**
         * Sets the improvement suggestion for the program.
         * @param improvementSuggestion the new improvement suggestion
         */
        public void setImprovementSuggestion(String improvementSuggestion) {
            this.improvementSuggestion = improvementSuggestion;
        }

        @Override
        public String toString() {
            return "Rating: " + rating + "/5\nReview: " + reviewText + "\nImprovement Suggestion: " + improvementSuggestion;
        }
    }

    // Map to store reviews for different programs
    private static Map<String, List<ProgramReview>> programReviews = new HashMap<>();

    /**
     * Private constructor to prevent instantiation of this class.
     * The class is designed to be used statically.
     */
    public FeedbackAndReviews() {
        // Constructor intentionally left blank.
    }

    /**
     * Submits a review for a specific program.
     * @param programTitle the title of the program
     * @param rating the rating given to the program (1 to 5)
     * @param reviewText the review text for the program
     * @param improvementSuggestion the suggestion for improving the program
     */
    public static void submitProgramReview(String programTitle, int rating, String reviewText, String improvementSuggestion) {
        ProgramReview review = new ProgramReview(rating, reviewText, improvementSuggestion);
        programReviews.computeIfAbsent(programTitle, k -> new ArrayList<>()).add(review);
        LOGGER.log(Level.INFO, "Review submitted for program: {0}", programTitle);
    }

    /**
     * Views all reviews for a specific program.
     * @param programTitle the title of the program
     */
    public static void viewProgramReview(String programTitle) {
        List<ProgramReview> reviews = programReviews.get(programTitle);
        if (reviews != null && !reviews.isEmpty()) {
            LOGGER.log(Level.INFO, "Reviews for program {0}:", programTitle);
            reviews.forEach(review -> LOGGER.log(Level.INFO, review.toString()));
        } else {
            LOGGER.log(Level.INFO, "No reviews available for program: {0}", programTitle);
        }
    }

    /**
     * Submits an improvement suggestion for the last review of a specific program.
     * @param programTitle the title of the program
     * @param improvementSuggestion the suggestion to improve the program
     */
    public static void submitImprovementSuggestion(String programTitle, String improvementSuggestion) {
        List<ProgramReview> reviews = programReviews.get(programTitle);
        if (reviews != null && !reviews.isEmpty()) {
            ProgramReview review = reviews.get(reviews.size() - 1);
            review.setImprovementSuggestion(improvementSuggestion);
            LOGGER.log(Level.INFO, "Improvement suggestion submitted for program: {0}", programTitle);
        } else {
            LOGGER.log(Level.WARNING, "No reviews found for program: {0}", programTitle);
        }
    }
}
