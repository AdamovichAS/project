package by.itacademy.jd2.ServiceDAO;
import by.itacademy.jd2.mysql_data.MysqlData;
import by.itacademy.jd2.user.User;
import by.itacademy.jd2.mysql_data.IMysqlData;
import by.itacademy.jd2.user_service.UserCreater;
import by.itacademy.jd2.user_service.UserFieldsSetter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static java.util.Objects.nonNull;

public enum ServiceDAO implements IServiceDAO {
    SERVICE_DATA_USER;

    private IMysqlData data;

    ServiceDAO() {
        data  = MysqlData.DATA;
    }

    @Override
    public boolean loginIsExist(String login)  {
        boolean result = false;
        String loginData = data.loginIsExist(login);
        if(nonNull(loginData)){
            if(loginData.equals(login)){
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean addNewUser(Map<String,String> userFieldsAndValues) {
        String login = userFieldsAndValues.get("login");
        if(loginIsExist(login)){
            return false;
        }
        User user = UserCreater.CREATER.createUser(userFieldsAndValues);
        data.addUser(user);
        return true;
    }

    @Override
    public boolean userIsExist(String login, String password) {
        boolean result = false;
        List<String> loginPassData = data.userIsExist(login, password);
        if(!loginPassData.isEmpty()){
            if(loginPassData.get(0).equals(login) && loginPassData.get(1).equals(password)){
                result = true;
            }
        }
        return result;

    }

    @Override
    public boolean updateUserInfo(Map<String, String> usersFieldsForUpdateWithLogin) {
        Map<String, String> userDataByLogin = data.getUserFieldsByLogin(usersFieldsForUpdateWithLogin.get("login"));
        if(userDataByLogin.isEmpty()){
            return false;
        }
        User dataUser = UserCreater.CREATER.createUser(userDataByLogin);
        UserFieldsSetter.SETTER.SetFields(dataUser,usersFieldsForUpdateWithLogin);
        return data.updateUserInfo(dataUser);
    }

    @Override
    public String getUserRoleByLogin(String login) {
        HashMap<String, String> userFieldsByLogin = data.getUserFieldsByLogin(login);
        return userFieldsByLogin.get("role");
    }

}
