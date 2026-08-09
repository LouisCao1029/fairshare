package nz.ac.auckland.se310.fairshare;

import java.util.HashSet;
import java.util.Set;

public class UserManager {
    private static UserManager instance;
    private final Set<Object> registeredEmails = new HashSet<>();

    private UserManager() {}

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public boolean registerUser(UserProfile profile) {

        String cleanEmail = profile.getEmail().trim().toLowerCase();

        if (registeredEmails.contains(cleanEmail)) {
            return false;
        }

        registeredEmails.add(cleanEmail);
        return true;
    }

    public void clearRegisteredEmails() {
        registeredEmails.clear();
    }
}
