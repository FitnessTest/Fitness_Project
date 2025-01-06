package special.planner;
import com.example.ContentManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContentManagementTest {

    private ContentManagement contentManagement;

    @BeforeEach
    void setUp() {
        contentManagement = new ContentManagement();
    }

    @Test
    void testApproveContent_ValidArticle() {
        contentManagement.approveOrRejectContent("Healthy Eating Tips", true);
        List<String> allContent = contentManagement.viewAllContent();
        assertTrue(allContent.contains("Healthy Eating Tips - Approved (article)"));
    }

    @Test
    void testRejectContent_ValidTip() {
        contentManagement.approveOrRejectContent("Yoga for Beginners", false);
        List<String> allContent = contentManagement.viewAllContent();
        assertTrue(allContent.contains("Yoga for Beginners - Rejected (tip)"));
    }

    @Test
    void testApproveContent_InvalidType() {
        contentManagement.approveOrRejectContent("5 Easy Smoothie Recipes", true);
        List<String> allContent = contentManagement.viewAllContent();
        assertTrue(allContent.contains("5 Easy Smoothie Recipes - Rejected (recipe)"));
    }

    @Test
    void testApproveContent_NotFound() {
        contentManagement.approveOrRejectContent("Nonexistent Content", true);
        List<String> allContent = contentManagement.viewAllContent();
        assertEquals(3, allContent.size()); // Ensure no changes were made
    }

    @Test
    void testViewAllContent() {
        List<String> allContent = contentManagement.viewAllContent();
        assertEquals(3, allContent.size());
        assertTrue(allContent.contains("Healthy Eating Tips - Pending (article)"));
        assertTrue(allContent.contains("Yoga for Beginners - Approved (tip)"));
        assertTrue(allContent.contains("5 Easy Smoothie Recipes - Rejected (recipe)"));
    }

    @Test
    void testHandleFeedback() {
        contentManagement.handleFeedback("Great content on healthy eating!");
        List<String> feedback = contentManagement.viewAllFeedback();
        assertEquals(1, feedback.size());
        assertTrue(feedback.contains("Great content on healthy eating!"));
    }

    @Test
    void testHandleComplaint() {
        contentManagement.handleComplaint("The smoothie recipes were not useful.");
        List<String> complaints = contentManagement.viewAllComplaints();
        assertEquals(1, complaints.size());
        assertTrue(complaints.contains("The smoothie recipes were not useful."));
    }

    @Test
    void testViewAllFeedback_Empty() {
        List<String> feedback = contentManagement.viewAllFeedback();
        assertTrue(feedback.isEmpty());
    }

    @Test
    void testViewAllComplaints_Empty() {
        List<String> complaints = contentManagement.viewAllComplaints();
        assertTrue(complaints.isEmpty());
    }
}