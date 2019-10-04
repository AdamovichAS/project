package by.itacademy.jd2.ServiceDAO;

import java.util.Map;

public interface IServiceDAO {

    boolean loginIsExist(String login);
    boolean addNewUser(Map<String,String> userFieldsAndValues);
    boolean userIsExist(String login, String password);
    boolean updateUserInfo(Map<String, String> usersFieldsForUpdateWithLogin);
    String getUserRoleByLogin(String login);
}
