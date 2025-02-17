package HW01.services;

import HW01.model.UserProfile;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    Map<String, UserProfile> userProfiles = new HashMap<>();


    public void putUserProfile(UserProfile userProfile) {
        userProfiles.put(userProfile.getLogin(), userProfile);
    }


    public UserProfile getUserProfile(String name) {
        return userProfiles
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().contains(name))
                .map(Map.Entry::getValue)
                .findFirst().
                orElse(new UserProfile("", "", ""));
    }


    public Map<String, UserProfile> getUserProfiles() {
        return userProfiles;
    }
}
