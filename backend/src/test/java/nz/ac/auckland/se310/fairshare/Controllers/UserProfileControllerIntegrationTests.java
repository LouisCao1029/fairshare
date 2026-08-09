package nz.ac.auckland.se310.fairshare.Controllers;

import nz.ac.auckland.se310.fairshare.UserManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void createProfileSuccessfully() throws Exception {

        String profileJson = """
                {
                    "username": "testuser",
                    "password": "password123",
                    "email": "test@example.com",
                    "country": "New Zealand",
                    "currency": "NZD"
                }
                """;

        mockMvc.perform(post("/profile")
                        .contentType("application/json")
                        .content(profileJson))
                .andExpect(status().isOk());
    }
}