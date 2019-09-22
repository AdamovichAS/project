package by.itacademy.jd2.users_data;

import by.itacademy.jd2.user.User;

import java.util.List;

public interface IUserData {
    boolean updateUserInfo(User user);
    User getUser(String login);
    boolean userIsExist(String login, String password);
    boolean addUser(User user);
    List<String> getUsersWithRole(User.ROLE role);
}
