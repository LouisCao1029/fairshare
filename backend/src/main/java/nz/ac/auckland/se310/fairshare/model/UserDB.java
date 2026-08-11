package nz.ac.auckland.se310.fairshare.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class UserDB {

  private final Set<User> registeredUsers = new HashSet<>();

  public synchronized void clear() {
    registeredUsers.clear();
  }

  public synchronized Optional<User> findUser(String email) {
    return registeredUsers.stream()
        .filter(u -> u.getEmail().equals(email.trim().toLowerCase()))
        .findFirst();
  }

  public synchronized void addUser(User user) {
    registeredUsers.add(user);
  }
}
