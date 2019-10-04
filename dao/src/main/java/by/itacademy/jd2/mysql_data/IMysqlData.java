package by.itacademy.jd2.mysql_data;


import by.itacademy.jd2.user.User;

import java.util.HashMap;
import java.util.List;

public interface IMysqlData {
    boolean updateUserInfo(User user);
    String loginIsExist(String login);
    List<String> userIsExist(String login, String password);
    boolean addUser(User user);
    HashMap<String,String> getUserFieldsByLogin(String login);

}
