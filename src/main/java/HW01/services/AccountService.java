package HW01.services;

import HW01.model.UserProfile;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    Map<String, UserProfile> userProfiles = new HashMap<>();
    Map<String, UserProfile> userProfileMap = new HashMap<String, UserProfile>();

    public void putUserProfile(UserProfile userProfile) {
        userProfiles.put(userProfile.getLogin(), userProfile);
    }

    public void putUserSession(String string, UserProfile userProfile) {
        userProfileMap.put(string, userProfile);
    }


    public UserProfile getUserProfile(String name) {
        return userProfiles
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().contains(name))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }


    public Map<String, UserProfile> getUserProfiles() {
        return userProfiles;
    }
}
