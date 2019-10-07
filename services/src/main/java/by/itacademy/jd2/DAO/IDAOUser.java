package by.itacademy.jd2.DAO;

import by.itacademy.jd2.user.User;

import java.util.Map;

public interface IDAOUser {

    boolean loginIsExist(String login);
    boolean addNewUser(Map<String,String> userFieldsAndValues);
    boolean userIsExist(String login, String password);
    boolean updateUserInfo(String login, Map<String, String> usersFieldsForUpdateWithLogin);

    User getUserByLogin(String login);
}
