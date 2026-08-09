package nz.ac.auckland.se310.fairshare.Controllers;

import nz.ac.auckland.se310.fairshare.UserProfile;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserProfileController {

  @PostMapping("/profile")
  public void createProfile(@RequestBody UserProfile profile) {

    System.out.println("========== PROFILE RECEIVED ==========");
    System.out.println("Age: " + profile.getAge());
    System.out.println("Password: " + profile.getPassword());
    System.out.println("Country: " + profile.getCountry());
    System.out.println("Currency: " + profile.getCurrency());
    System.out.println("======================================");
  }
}
