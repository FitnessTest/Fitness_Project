package special.planner;
import com.example.ProgramExplorationAndEnrollment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProgramExplorationAndEnrollmentTest {

    @BeforeEach
    void setUp() {

        ProgramExplorationAndEnrollment.programs = new ArrayList<>();
        new ProgramExplorationAndEnrollment();
    }

    @Test
    void testListPrograms_WithPrograms() {
        List<ProgramExplorationAndEnrollment.Program> programs = ProgramExplorationAndEnrollment.getPrograms();
        ProgramExplorationAndEnrollment.listPrograms(programs);

        assertEquals(3, programs.size());
        assertEquals("Weight Loss Bootcamp", programs.get(0).getTitle());
        assertEquals("Beginner", programs.get(0).getDifficultyLevel());
    }

    @Test
    void testListPrograms_NoPrograms() {
        ProgramExplorationAndEnrollment.listPrograms(List.of());
        assertTrue(ProgramExplorationAndEnrollment.getPrograms().isEmpty());
    }

    @Test
    void testBrowsePrograms() {
        List<ProgramExplorationAndEnrollment.Program> results = ProgramExplorationAndEnrollment.browsePrograms("Beginner", null, 0, 100);
        assertEquals(1, results.size());
        assertEquals("Weight Loss Bootcamp", results.get(0).getTitle());
    }

    @Test
    void testEnrollInProgram_Success() {
        ProgramExplorationAndEnrollment.enrollInProgram("Weight Loss Bootcamp");
    }

    @Test
    void testEnrollInProgram_NotFound() {
        ProgramExplorationAndEnrollment.enrollInProgram("Nonexistent Program");
    }

    @Test
    void testViewSchedule_Success() {
        ProgramExplorationAndEnrollment.viewSchedule("Muscle Building Challenge");
    }

    @Test
    void testViewSchedule_NotFound() {
        ProgramExplorationAndEnrollment.viewSchedule("Nonexistent Program");
    }
}