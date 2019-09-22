package by.itacademy.jd2.ServiceDAO;




import by.itacademy.jd2.user.User;
import by.itacademy.jd2.users_data.IUserData;
import by.itacademy.jd2.users_data.UsersData;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public enum DataService implements ServiceDAO{
    SERVICE_DATA_USER;

    private Field[] userFields;
    private IUserData userData;

    DataService() {
        userFields = User.class.getDeclaredFields();
        userData  = UsersData.USERS_DATA;
    }

    @Override
    public User getUserByLogin(String login)  {
        return userData.getUser(login);
    }

    @Override
    public boolean addNewUser(User newUser) {
        return userData.addUser(newUser);
    }

    @Override
    public boolean userIsExist(String login, String password) {
        return userData.userIsExist(login, password);

    }

    @Override
    public boolean updateUserInfo(Map<String, String> usersFieldsForUpdate, String userLogin) {
        User user = getUserByLogin(userLogin);
         for (Field userField : userFields) {
            if(usersFieldsForUpdate.containsKey(userField.getName())){
                String fieldNameForUpdate = userField.getName();
                String fieldValueForUpdate = usersFieldsForUpdate.get(fieldNameForUpdate);
                updateUserField(user,fieldNameForUpdate,fieldValueForUpdate);
            }
        }
        return userData.updateUserInfo(user);
    }

    @Override
    public List<String> getUsersLogin(User.ROLE role) {
        return userData.getUsersWithRole(role);
    }


    private void updateUserField(User user,String fieldNameForUpdate,String fieldValueForUpdate){
        switch (fieldNameForUpdate){
            case "password":
                user.setPassword(fieldValueForUpdate);
                break;
            case "money":
                user.setMoney(Integer.parseInt(fieldValueForUpdate));
                break;

        }
    }
}
