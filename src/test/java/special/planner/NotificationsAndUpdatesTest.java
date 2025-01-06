package special.planner;


import com.example.NotificationsAndUpdates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotificationsAndUpdatesTest {

    @BeforeEach
    void setUp() {

        new NotificationsAndUpdates();
    }

    @Test
    void testAddClient() {

        NotificationsAndUpdates.addClient("Amr Jamhor", "amr.jamhor@example.com");
        NotificationsAndUpdates.addClient("Ihab Habash", "ihab.habash@example.com");
        NotificationsAndUpdates.addClient("Ameed Diab", "ameed.diab@example.com");


        assertEquals(3, getClients().size());
        assertEquals("Amr Jamhor", getClients().get(0).getName());
        assertEquals("ihab.habash@example.com", getClients().get(1).getEmail());
        assertEquals("Ameed Diab", getClients().get(2).getName());
    }

    @Test
    void testNotifyScheduleChange() {

        NotificationsAndUpdates.addClient("Amr Jamhor", "amr.jamhor@example.com");


        NotificationsAndUpdates.notifyScheduleChange("Java Bootcamp", "10:00 AM, Monday");


        List<String> notifications = getClients().get(0).getNotifications();
        assertEquals(1, notifications.size());
        assertEquals("The schedule for the program \"Java Bootcamp\" has been changed. The new schedule is: 10:00 AM, Monday", notifications.get(0));
    }

    @Test
    void testAnnounceNewProgramOrOffer() {

        NotificationsAndUpdates.addClient("Ihab Habash", "ihab.habash@example.com");


        NotificationsAndUpdates.announceNewProgramOrOffer("50% discount on all courses!");


        List<String> notifications = getClients().get(0).getNotifications();
        assertEquals(1, notifications.size());
        assertEquals("Announcement: 50% discount on all courses!", notifications.get(0));
    }

    @Test
    void testListAllClientsNotifications_NoClients() {

        NotificationsAndUpdates.listAllClientsNotifications();


        assertTrue(getClients().isEmpty());
    }

    @Test
    void testListAllClientsNotifications_WithClients() {

        NotificationsAndUpdates.addClient("Ameed Diab", "ameed.diab@example.com");
        NotificationsAndUpdates.announceNewProgramOrOffer("New Python Bootcamp starting soon!");


        NotificationsAndUpdates.listAllClientsNotifications();


        assertEquals(1, getClients().size());
        assertEquals(1, getClients().get(0).getNotifications().size());
        assertEquals("Announcement: New Python Bootcamp starting soon!", getClients().get(0).getNotifications().get(0));
    }


    private List<NotificationsAndUpdates.Client> getClients() {
        return NotificationsAndUpdates.clients;
    }
}