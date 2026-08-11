package nz.ac.auckland.se310.fairshare;

import nz.ac.auckland.se310.fairshare.model.User;
import nz.ac.auckland.se310.fairshare.model.UserDB;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserDB userDB;
  private final PasswordEncoder encoder;

  public UserService(UserDB userDB, PasswordEncoder encoder) {
    this.userDB = userDB;
    this.encoder = encoder;
  }

  public synchronized void register(User user) {

    if (userDB.findUser(user.getEmail()).isPresent()) {
      throw new IllegalArgumentException("Email already in use");
    }

    String hashedPassword = encoder.encode(user.getPassword());
    user.setPassword(hashedPassword);
    user.setEmail(user.getEmail().trim().toLowerCase());

    userDB.addUser(user);
  }
}
