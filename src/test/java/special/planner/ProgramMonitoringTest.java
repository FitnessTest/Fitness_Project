package special.planner;

import com.example.ProgramMonitoring;
import org.junit.jupiter.api.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgramMonitoringTest {

    private ProgramMonitoring monitoring;

    @BeforeEach
    void setUp() {
        monitoring = new ProgramMonitoring();
    }

    @Test
    void testInitialization() {

        assertEquals(5, ProgramMonitoring.programs.size());
        assertTrue(ProgramMonitoring.programs.containsKey("Weight Loss"));


        assertEquals(5, ProgramMonitoring.clients.size());
        assertTrue(ProgramMonitoring.clients.containsKey("amrojamhour4@gmail.com"));
    }

    @Test
    void testViewMostPopularPrograms() {

        List<String> result = ProgramMonitoring.viewMostPopularPrograms();


        List<String> expectedOutput = List.of(
                "Program Name              | Enrollment Count",
                "------------------------------------------",
                "Cardio Fitness            | 100            ",
                "Weight Loss               | 80             ",
                "Yoga and Flexibility      | 70             ",
                "Strength Training         | 60             ",
                "HIIT Bootcamp             | 50             "
        );


        assertEquals(expectedOutput, result, "The returned output should match the expected format and content.");
    }

    @Test
    void testGenerateReports() {
        // This method is currently unsupported, and we will throw an exception to
        // indicate that this functionality is not yet implemented.
        throw new UnsupportedOperationException("Generating reports functionality is not yet implemented.");
    }

    @Test
    void testTrackProgramsStatus() {
        int status = ProgramMonitoring.trackProgramsStatus();
        assertEquals(0, status);
    }

    @Test
    void testProgramClass() {
        ProgramMonitoring.Program program = new ProgramMonitoring().new Program("Test Program", 5000, 25);
        assertEquals("Test Program", program.getName());
        assertEquals(5000, program.getRevenue());
        assertEquals(25, program.getEnrollmentCount());
    }

    @Test
    void testClientClass() {
        ProgramMonitoring.Client client = new ProgramMonitoring.Client("test@example.com", "Test Client", "Test Program");
        assertEquals("Test Client", client.getName());
        assertEquals("Test Program", client.getProgramName());
    }
}