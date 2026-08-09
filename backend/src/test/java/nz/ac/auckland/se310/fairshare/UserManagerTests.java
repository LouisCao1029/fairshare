package nz.ac.auckland.se310.fairshare;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTests {

    private UserManager userManager;

    @BeforeEach
    void setUp() {
        userManager = UserManager.getInstance();
        userManager.clearRegisteredEmails();
    }

    @Test
    void testRegisterNewUser() {
        UserProfile profile = new UserProfile(
                "testuser",
                "password123",
                "test@example.com",
                "New Zealand",
                "NZD"
        );

        boolean result = userManager.registerUser(profile);

        assertTrue(result);
    }

    @Test
    void testRejectDuplicateEmail() {
        UserProfile firstProfile = new UserProfile(
                "user1",
                "password123",
                "test@example.com",
                "New Zealand",
                "NZD"
        );

        UserProfile secondProfile = new UserProfile(
                "user2",
                "password123",
                "test@example.com",
                "New Zealand",
                "NZD"
        );

        assertTrue(userManager.registerUser(firstProfile));
        assertFalse(userManager.registerUser(secondProfile));
    }

    @Test
    void testEmailIsCaseInsensitive() {
        UserProfile firstProfile = new UserProfile(
                "user1",
                "password123",
                "Test@Example.com",
                "New Zealand",
                "NZD"
        );

        UserProfile secondProfile = new UserProfile(
                "user2",
                "password123",
                "test@example.com",
                "New Zealand",
                "NZD"
        );

        assertTrue(userManager.registerUser(firstProfile));
        assertFalse(userManager.registerUser(secondProfile));
    }

    @Test
    void testEmailWhitespaceIsIgnored() {
        UserProfile firstProfile = new UserProfile(
                "user1",
                "password123",
                "test@example.com",
                "New Zealand",
                "NZD"
        );

        UserProfile secondProfile = new UserProfile(
                "user2",
                "password123",
                "  test@example.com  ",
                "New Zealand",
                "NZD"
        );

        assertTrue(userManager.registerUser(firstProfile));
        assertFalse(userManager.registerUser(secondProfile));
    }
}