package special.planner;

import com.example.ProgramExplorationAndEnrollment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgramExplorationAndEnrollmentTest {

    @BeforeEach
    void setUp() {
        ProgramExplorationAndEnrollment.programs = new ArrayList<>();
        new ProgramExplorationAndEnrollment();
    }

    @Test
    void testListPrograms_WithPrograms() {
        List<ProgramExplorationAndEnrollment.Program> programs = ProgramExplorationAndEnrollment.programs;
        assertEquals(3, programs.size());
        assertEquals("Weight Loss Bootcamp", programs.get(0).getTitle());
    }

    @Test
    void testListPrograms_NoPrograms() {
        // Creating an empty list to simulate no programs
        List<ProgramExplorationAndEnrollment.Program> emptyList = new ArrayList<>();

        // Ensuring the method handles an empty list without exceptions
        List<ProgramExplorationAndEnrollment.Program> result = ProgramExplorationAndEnrollment.listPrograms(emptyList);

        // Assertion: The returned list should also be empty
        assertTrue(result.isEmpty(), "The result list should be empty when no programs are available.");
    }

    @Test
    void testBrowsePrograms() {
        List<ProgramExplorationAndEnrollment.Program> results =
                ProgramExplorationAndEnrollment.browsePrograms("Beginner", null, 0, 100);
        assertEquals(1, results.size());
        assertEquals("Weight Loss Bootcamp", results.get(0).getTitle());
    }

    @Test
    void testEnrollInProgram_Success() {
        ProgramExplorationAndEnrollment.enrollInProgram("Weight Loss Bootcamp");
        assertTrue(ProgramExplorationAndEnrollment.isEnrolled("Weight Loss Bootcamp"));
    }

    @Test
    void testEnrollInProgram_NotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> ProgramExplorationAndEnrollment.enrollInProgram("Nonexistent Program"));
        assertEquals("Program not found: Nonexistent Program", exception.getMessage());
    }

    @Test
    void testViewSchedule_Success() {
        String schedule = ProgramExplorationAndEnrollment.viewSchedule("Muscle Building Challenge");
        assertEquals("Tue-Thu 11:00 AM", schedule);
    }

    @Test
    void testViewSchedule_NotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> ProgramExplorationAndEnrollment.viewSchedule("Nonexistent Program"));
        assertEquals("Program not found: Nonexistent Program", exception.getMessage());
    }
}
