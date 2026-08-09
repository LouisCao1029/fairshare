package nz.ac.auckland.se310.fairshare.Controllers;

import nz.ac.auckland.se310.fairshare.UserManager;
import nz.ac.auckland.se310.fairshare.UserProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserProfileController {

  private final UserManager userManager = UserManager.getInstance();

  @PostMapping("/profile")
  public ResponseEntity<String> createProfile(@RequestBody UserProfile profile) {

    boolean isRegistered = userManager.registerUser(profile);

    if (isRegistered) {
      System.out.println("========== PROFILE SUCCESSFULLY CREATED ==========");
      return ResponseEntity.ok("Profile created successfully.");
    } else {
      System.out.println("========== PROFILE CREATION FAILED: EMAIL ALREADY REGISTERED ==========");
      return ResponseEntity.status(409).body("Email already registered.");
    }
  }
}
