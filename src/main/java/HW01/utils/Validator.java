package HW01.utils;

import HW01.model.UserProfile;
import HW01.services.AccountService;

import java.security.PublicKey;
import java.util.regex.Pattern;

public class Validator {
    public static boolean isValidEmail(String email) {
        return Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$", email);
    }

    public static boolean isValidUserName(String username) {
        return Pattern.matches("^[a-zA-Z0-9._%+-]+$", username);
    }

    public static boolean isValidPassword(String password) {
        return password.length() > 6;
    }

    public static boolean isUserMailExits(AccountService accountService, UserProfile userProfile, String email) {
        return accountService.
                getUserProfiles()
                .values()
                .stream()
                .anyMatch(user -> user.getEmail().equals(userProfile.getEmail()));
    }

    public static boolean isUserNameExist(AccountService accountService, UserProfile userProfile, String userName) {
       return accountService
                .getUserProfiles()
                .values()
                .stream()
                .anyMatch(user -> user.getLogin().equals(userProfile.getLogin()));
    }
}
