package HW01.services;

import HW01.dbservice.DBService;
import HW01.dbservice.dataset.UsersDataSet;
import HW01.exceptions.DBException;
import HW01.model.UserProfile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccountService {


    DBService dbService;

    public AccountService(DBService dbService) {
        this.dbService = dbService;
    }

    public void putUserProfile(UserProfile userProfile) {
        try {
            dbService.addUser(userProfile.getLogin(), userProfile.getEmail(), userProfile.getPassword());
        } catch (DBException e) {
            e.printStackTrace();
        }

    }


    public UserProfile getUserProfile(String name) {
        try {
            UsersDataSet dataSet = dbService.getUser(name);
            return new UserProfile(dataSet.getLogin(), dataSet.getEmail(), dataSet.getPassword());
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
    }


    public List<UserProfile> getUserProfiles() {
        List<UserProfile> userProfileList = new ArrayList<>();
        List<UsersDataSet> usersList = dbService.getAllUsers();
        for (UsersDataSet item : usersList) {
            userProfileList.add(new UserProfile(item.getLogin(), item.getEmail(), item.getPassword()));
        }

        return userProfileList;
    }
}
