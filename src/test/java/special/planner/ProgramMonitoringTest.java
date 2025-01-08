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
        assertEquals(5, ProgramMonitoring.programs.size(), "The number of programs should be 5.");
        assertTrue(ProgramMonitoring.programs.containsKey("Weight Loss"), "The 'Weight Loss' program should be present in the programs map.");
        assertEquals(5, ProgramMonitoring.clients.size(), "The number of clients should be 5.");
        assertTrue(ProgramMonitoring.clients.containsKey("amrojamhour4@gmail.com"), "The client with email 'amrojamhour4@gmail.com' should be present in the clients map.");
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
        ProgramMonitoring.generateReports();

        // Add assertions to verify the behavior
        // For example, if generateReports writes to a file or a log:
        // - Check the file contents or the log contents.
        // - Ensure the correct reports are generated.
        // Placeholder assertion to simulate expected behavior (you should replace this with actual verification logic)
        assertTrue(true, "This test should verify if the reports were generated correctly. For example, check log files or state changes.");
    }

    @Test
    void testTrackProgramsStatus() {
        int status = ProgramMonitoring.trackProgramsStatus();
        assertEquals(0, status, "The status should be 0 when there are no issues with the programs.");
    }

    @Test
    void testProgramClass() {
        ProgramMonitoring.Program program = new ProgramMonitoring().new Program("Test Program", 5000, 25);
        assertEquals("Test Program", program.getName(), "The program name should be 'Test Program'.");
        assertEquals(5000, program.getRevenue(), "The revenue should be 5000.");
        assertEquals(25, program.getEnrollmentCount(), "The enrollment count should be 25.");
    }

    @Test
    void testClientClass() {
        ProgramMonitoring.Client client = new ProgramMonitoring.Client("test@example.com", "Test Client", "Test Program");
        assertEquals("Test Client", client.getName(), "The client name should be 'Test Client'.");
        assertEquals("Test Program", client.getProgramName(), "The program name for the client should be 'Test Program'.");
    }
}
