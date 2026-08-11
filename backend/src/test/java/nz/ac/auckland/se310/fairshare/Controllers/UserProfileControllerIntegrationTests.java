package nz.ac.auckland.se310.fairshare.Controllers;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import nz.ac.auckland.se310.fairshare.model.User;
import nz.ac.auckland.se310.fairshare.model.UserDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ActiveProfiles("test")
class UserProfileControllerIntegrationTests {

  @Autowired private WebApplicationContext context;

  @Autowired private UserDB userDB;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    userDB.clear();
  }

  @Test
  void createProfileWithValidDetailsReturns201() throws Exception {

    String profileJson =
        """
        {
            "username": "testuser",
            "password": "password123",
            "email": "test@example.com",
            "country": "NEW_ZEALAND",
            "currency": "NZD"
        }
        """;

    mockMvc
        .perform(
            post("/users/register").contentType(MediaType.APPLICATION_JSON).content(profileJson))
        .andExpect(status().isCreated())
        .andExpect(content().string("User registered successfully"));
  }

  @Test
  void createProfileWithDuplicateEmailReturns409() throws Exception {

    String profileJson =
        """
        {
            "username": "testuser",
            "password": "password123",
            "email": "test@example.com",
            "country": "NEW_ZEALAND",
            "currency": "NZD"
        }
        """;

    mockMvc
        .perform(
            post("/users/register").contentType(MediaType.APPLICATION_JSON).content(profileJson))
        .andExpect(status().isCreated());

    mockMvc
        .perform(
            post("/users/register").contentType(MediaType.APPLICATION_JSON).content(profileJson))
        .andExpect(status().isConflict())
        .andExpect(content().string("Email is already in use"));
  }

  @Test
  void duplicateEmailWithDifferentCapitalisationReturns409() throws Exception {

    String firstProfile =
        """
        {
            "username": "user1",
            "password": "password123",
            "email": "Test@Example.com",
            "country": "NEW_ZEALAND",
            "currency": "NZD"
        }
        """;

    String secondProfile =
        """
        {
            "username": "user2",
            "password": "password123",
            "email": "test@example.com",
            "country": "NEW_ZEALAND",
            "currency": "NZD"
        }
        """;

    mockMvc
        .perform(
            post("/users/register").contentType(MediaType.APPLICATION_JSON).content(firstProfile))
        .andExpect(status().isCreated());

    mockMvc
        .perform(
            post("/users/register").contentType(MediaType.APPLICATION_JSON).content(secondProfile))
        .andExpect(status().isConflict());
  }

  @Test
  void duplicateEmailWithWhitespaceReturns409() throws Exception {

    String firstProfile =
        """
        {
            "username": "user1",
            "password": "password123",
            "email": "test@example.com",
            "country": "NEW_ZEALAND",
            "currency": "NZD"
        }
        """;

    String secondProfile =
        """
        {
            "username": "user2",
            "password": "password123",
            "email": "  test@example.com  ",
            "country": "NEW_ZEALAND",
            "currency": "NZD"
        }
        """;

    mockMvc
        .perform(
            post("/users/register").contentType(MediaType.APPLICATION_JSON).content(firstProfile))
        .andExpect(status().isCreated());

    mockMvc
        .perform(
            post("/users/register").contentType(MediaType.APPLICATION_JSON).content(secondProfile))
        .andExpect(status().isConflict());
  }

  @Test
  void createProfileSuccessfullyPasswordHashSuccessful() throws Exception {
    String rawPassword = "password123";

    String firstProfile =
        """
        {
            "username": "user1",
            "password": "password123",
            "email": "test@example.com",
            "country": "NEW_ZEALAND",
            "currency": "NZD"
        }
        """;

    mockMvc
        .perform(
            post("/users/register").contentType(MediaType.APPLICATION_JSON).content(firstProfile))
        .andExpect(status().isCreated());

    User user = userDB.findUser("test@example.com").orElseThrow();
    assertNotEquals(rawPassword, user.getPassword());
  }
}
