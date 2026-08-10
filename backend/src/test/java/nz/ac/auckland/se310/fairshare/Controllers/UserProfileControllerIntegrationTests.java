package nz.ac.auckland.se310.fairshare.Controllers;

import nz.ac.auckland.se310.fairshare.UserManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserProfileControllerIntegrationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        UserManager.getInstance().clearRegisteredEmails();
    }

    @Test
    void createProfileWithValidDetailsReturns200() throws Exception {

        String profileJson = """
                {
                    "username": "testuser",
                    "password": "password123",
                    "email": "test@example.com",
                    "country": "NEW_ZEALAND",
                    "currency": "NZD"
                }
                """;

        mockMvc.perform(post("/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(profileJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Profile created successfully."));
    }

    @Test
    void createProfileWithDuplicateEmailReturns409() throws Exception {

        String profileJson = """
                {
                    "username": "testuser",
                    "password": "password123",
                    "email": "test@example.com",
                    "country": "NEW_ZEALAND",
                    "currency": "NZD"
                }
                """;

        // First profile should be created successfully
        mockMvc.perform(post("/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(profileJson))
                .andExpect(status().isOk());

        // Second profile with the same email should fail
        mockMvc.perform(post("/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(profileJson))
                .andExpect(status().isConflict())
                .andExpect(content().string("Email already registered."));
    }

    @Test
    void duplicateEmailWithDifferentCapitalisationReturns409() throws Exception {

        String firstProfile = """
                {
                    "username": "user1",
                    "password": "password123",
                    "email": "Test@Example.com",
                    "country": "NEW_ZEALAND",
                    "currency": "NZD"
                }
                """;

        String secondProfile = """
                {
                    "username": "user2",
                    "password": "password123",
                    "email": "test@example.com",
                    "country": "NEW_ZEALAND",
                    "currency": "NZD"
                }
                """;

        mockMvc.perform(post("/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(firstProfile))
                .andExpect(status().isOk());

        mockMvc.perform(post("/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(secondProfile))
                .andExpect(status().isConflict());
    }

    @Test
    void duplicateEmailWithWhitespaceReturns409() throws Exception {

        String firstProfile = """
                {
                    "username": "user1",
                    "password": "password123",
                    "email": "test@example.com",
                    "country": "NEW_ZEALAND",
                    "currency": "NZD"
                }
                """;

        String secondProfile = """
                {
                    "username": "user2",
                    "password": "password123",
                    "email": "  test@example.com  ",
                    "country": "NEW_ZEALAND",
                    "currency": "NZD"
                }
                """;

        mockMvc.perform(post("/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(firstProfile))
                .andExpect(status().isOk());

        mockMvc.perform(post("/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(secondProfile))
                .andExpect(status().isConflict());
    }
}