package nz.ac.auckland.se310.fairshare;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileTests {

    @Test
    void testConstructor() {
        UserProfile profile = new UserProfile(
                "testuser",
                "password123",
                "test@example.com",
                "New Zealand",
                "NZD"
        );

        assertEquals("testuser", profile.getUsername());
        assertEquals("password123", profile.getPassword());
        assertEquals("test@example.com", profile.getEmail());
        assertEquals("New Zealand", profile.getCountry());
        assertEquals("NZD", profile.getCurrency());
    }

    @Test
    void testDefaultConstructor() {
        UserProfile profile = new UserProfile();

        assertNull(profile.getUsername());
        assertNull(profile.getPassword());
        assertNull(profile.getEmail());
        assertNull(profile.getCountry());
        assertNull(profile.getCurrency());
    }

    @Test
    void testSetUsername() {
        UserProfile profile = new UserProfile();

        profile.setUsername("testuser");

        assertEquals("testuser", profile.getUsername());
    }

    @Test
    void testSetPassword() {
        UserProfile profile = new UserProfile();

        profile.setPassword("password123");

        assertEquals("password123", profile.getPassword());
    }

    @Test
    void testSetEmail() {
        UserProfile profile = new UserProfile();

        profile.setEmail("test@example.com");

        assertEquals("test@example.com", profile.getEmail());
    }

    @Test
    void testSetCountry() {
        UserProfile profile = new UserProfile();

        profile.setCountry("New Zealand");

        assertEquals("New Zealand", profile.getCountry());
    }

    @Test
    void testSetCurrency() {
        UserProfile profile = new UserProfile();

        profile.setCurrency("NZD");

        assertEquals("NZD", profile.getCurrency());
    }
}