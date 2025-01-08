package special.planner;

import com.example.ProgramManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgramManagementTest {

    @BeforeEach
    void setUp() {
        new ProgramManagement();  // Reset programs list before each test
    }

    @Test
    void testCreateProgram() {
        ProgramManagement.createProgram("Yoga Basics", "4 weeks", "Beginner", "Weight loss",
                "http://tutorial.com", "yoga.jpg", "yoga_doc.pdf", 49.99, "Mon-Wed 9:00 AM");
        List<ProgramManagement.Program> programs = ProgramManagement.getPrograms();
        assertEquals(1, programs.size());
        assertEquals("Yoga Basics", programs.get(0).title);
    }

    @Test
    void testUpdateProgram_Success() {
        ProgramManagement.createProgram("Yoga Basics", "4 weeks", "Beginner", "Weight loss",
                "http://tutorial.com", "yoga.jpg", "yoga_doc.pdf", 49.99, "Mon-Wed 9:00 AM");
        boolean result = ProgramManagement.updateProgram("Yoga Basics", "Advanced Yoga", "6 weeks", "Advanced",
                "Flexibility", "http://newtutorial.com", "advanced_yoga.jpg", "advanced_yoga_doc.pdf", 79.99, "Mon-Wed 10:00 AM");
        assertTrue(result);
        List<ProgramManagement.Program> programs = ProgramManagement.getPrograms();
        assertEquals("Advanced Yoga", programs.get(0).title);
    }

    @Test
    void testUpdateProgram_NotFound() {
        boolean result = ProgramManagement.updateProgram("Nonexistent Program", "Updated Yoga", "6 weeks", "Advanced",
                "Flexibility", "http://newtutorial.com", "advanced_yoga.jpg", "advanced_yoga_doc.pdf", 79.99, "Mon-Wed 10:00 AM");
        assertFalse(result);
    }

    @Test
    void testDeleteProgram_Success() {
        ProgramManagement.createProgram("Yoga Basics", "4 weeks", "Beginner", "Weight loss",
                "http://tutorial.com", "yoga.jpg", "yoga_doc.pdf", 49.99, "Mon-Wed 9:00 AM");
        boolean result = ProgramManagement.deleteProgram("Yoga Basics");
        assertTrue(result);
        List<ProgramManagement.Program> programs = ProgramManagement.getPrograms();
        assertTrue(programs.isEmpty());
    }

    @Test
    void testDeleteProgram_NotFound() {
        boolean result = ProgramManagement.deleteProgram("Nonexistent Program");
        assertFalse(result);
    }

    @Test
    void testListAllPrograms_Empty() {
        // Ensure the list is empty
        List<ProgramManagement.Program> programs = ProgramManagement.getPrograms();
        assertTrue(programs.isEmpty(), "The list of programs should be empty.");


        ProgramManagement.listAllPrograms(); // Should log "No programs available."
    }

    @Test
    void testListAllPrograms_WithPrograms() {
        ProgramManagement.createProgram("Yoga Basics", "4 weeks", "Beginner", "Weight loss",
                "http://tutorial.com", "yoga.jpg", "yoga_doc.pdf", 49.99, "Mon-Wed 9:00 AM");
        ProgramManagement.createProgram("Advanced Yoga", "6 weeks", "Advanced", "Flexibility",
                "http://newtutorial.com", "advanced_yoga.jpg", "advanced_yoga_doc.pdf", 79.99, "Mon-Wed 10:00 AM");

        List<ProgramManagement.Program> programs = ProgramManagement.getPrograms();


        assertEquals(2, programs.size(), "There should be two programs in the list.");
        assertEquals("Yoga Basics", programs.get(0).title, "The first program should be 'Yoga Basics'.");
        assertEquals("Advanced Yoga", programs.get(1).title, "The second program should be 'Advanced Yoga'.");


        ProgramManagement.listAllPrograms();
    }
}
