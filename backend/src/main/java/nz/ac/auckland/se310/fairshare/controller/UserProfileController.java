package nz.ac.auckland.se310.fairshare.controller;

import nz.ac.auckland.se310.fairshare.model.User;
import nz.ac.auckland.se310.fairshare.model.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserProfileController {

  private final UserDB userDB;

  @Autowired
  public UserProfileController(UserDB userDB) {
    this.userDB = userDB;
  }

  @PostMapping("/profile")
  public ResponseEntity<String> createProfile(@RequestBody User user) {
    if (userDB.findUser(user.getEmail()).isPresent()) {
      return ResponseEntity.status(409).body("Email already registered.");
    }

    userDB.addUser(user);
    return ResponseEntity.ok("Profile created successfully.");
  }
}
