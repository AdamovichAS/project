package by.itacademy.jd2.ServiceDAO;

import by.itacademy.jd2.user.User;

import java.util.List;
import java.util.Map;

public interface IServiceDAO {

    User getUserByLogin(String login);
    boolean addNewUser(User newUser);
    boolean userIsExist(String login, String password);
    boolean updateUserInfo(Map<String,String> usersFieldsForUpdate, String userLogin);
    List<String> getUsersLogin(User.ROLE role);



}
