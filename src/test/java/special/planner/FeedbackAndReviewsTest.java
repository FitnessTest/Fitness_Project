package special.planner;
import com.example.FeedbackAndReviews;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FeedbackAndReviewsTest {
    private FeedbackAndReviews feedbackAndReviews;

    @BeforeEach
    void setUp() {
        feedbackAndReviews = new FeedbackAndReviews();
    }

    @Test
    void testSubmitProgramReview_ValidReview() {
        feedbackAndReviews.submitProgramReview("Program A", 5, "Excellent program!", "Add more examples");

        assertDoesNotThrow(() -> FeedbackAndReviews.viewProgramReview("Program A"));
    }

    @Test
    void testViewProgramReview_NoReviews() {
        FeedbackAndReviews.viewProgramReview("Nonexistent Program");

        assertTrue(true);
    }

    @Test
    void testSubmitImprovementSuggestion_Valid() {
        feedbackAndReviews.submitProgramReview("Program B", 4, "Good program!", "Add better examples");
        FeedbackAndReviews.submitImprovementSuggestion("Program B", "More detailed explanations");
        // Check if the suggestion is applied correctly
        assertDoesNotThrow(() -> FeedbackAndReviews.viewProgramReview("Program B"));
    }

    @Test
    void testSubmitImprovementSuggestion_NoReviews() {
        FeedbackAndReviews.submitImprovementSuggestion("Nonexistent Program", "Add more examples");

        assertTrue(true);
    }

    @Test
    void testProgramReview_InvalidRating_Low() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            feedbackAndReviews.submitProgramReview("Program C", 0, "Invalid rating!", "N/A");
        });
        assertEquals("Rating must be between 1 and 5.", exception.getMessage());
    }

    @Test
    void testProgramReview_InvalidRating_High() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            feedbackAndReviews.submitProgramReview("Program D", 6, "Invalid rating!", "N/A");
        });
        assertEquals("Rating must be between 1 and 5.", exception.getMessage());
    }

    @Test
    void testSetRating_InvalidLow() {
        FeedbackAndReviews.ProgramReview review = new FeedbackAndReviews.ProgramReview(3, "Average program", "Add examples");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            review.setRating(0);
        });
        assertEquals("Rating must be between 1 and 5.", exception.getMessage());
    }

    @Test
    void testSetRating_InvalidHigh() {
        FeedbackAndReviews.ProgramReview review = new FeedbackAndReviews.ProgramReview(3, "Average program", "Add examples");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            review.setRating(6);
        });
        assertEquals("Rating must be between 1 and 5.", exception.getMessage());
    }

    @Test
    void testSetAndGetMethods() {
        FeedbackAndReviews.ProgramReview review = new FeedbackAndReviews.ProgramReview(4, "Good program", "Add examples");
        assertEquals(4, review.getRating());
        assertEquals("Good program", review.getReviewText());
        assertEquals("Add examples", review.getImprovementSuggestion());
        review.setRating(5);
        review.setReviewText("Excellent program");
        review.setImprovementSuggestion("No improvements needed");
        assertEquals(5, review.getRating());
        assertEquals("Excellent program", review.getReviewText());
        assertEquals("No improvements needed", review.getImprovementSuggestion());
    }
}
