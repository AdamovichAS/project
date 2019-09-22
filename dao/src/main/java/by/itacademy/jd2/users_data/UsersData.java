package by.itacademy.jd2.users_data;



import by.itacademy.jd2.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

public enum UsersData{
    USERS_DATA;

    private Map<String, User> users;

    UsersData() {
        users = new HashMap<>();
        users.put("a",new User("a","a",10,User.ROLE.ADMIN));
        users.put("b",new User("b","b",10,User.ROLE.USER));
        users.put("c",new User("c","v",10,User.ROLE.USER));
    }


   /* public User getUserByLogin(String login) {
        User result = null;
        if (users.containsKey(login)) {
            result = users.get(login);
        }
        return result;
    }
    public boolean userIsExist(String login, String password) {

        boolean result = false;
        User user = USERS_DATA.getUserByLogin(login);
        if(nonNull(user)){
            if(user.getPassword().equals(password)){
                result=true;
            }
        }

        return result;
    }

    public boolean add(User user) {
        boolean result = false;
        if (!users.containsKey(user.getLogin())) {
            users.put(user.getLogin(), user);
            result =true;
        }
        return result;
    }*/

    public boolean updateUserInfo(User user){
        boolean result = false;
        if(nonNull(USERS_DATA.getUser(user.getLogin()))){
            users.replace(user.getLogin(),user);
            result =true;
        }
        return result;
    }


    public User getUser(String login) {
        User result = null;
        if (users.containsKey(login)) {
            result = users.get(login);
        }
        return result;
    }


    public boolean userIsExist(String login, String password) {
        boolean result = false;
        User user = USERS_DATA.getUser(login);
        if(nonNull(user)){
            if(user.getPassword().equals(password)){
                result=true;
            }
        }

        return result;
    }



    public boolean addUser(User user) {
        boolean result = false;
        if(!users.containsKey(user.getLogin())){
            users.put(user.getLogin(),user);
            result = true;
        }
        return result;
    }

    public List<String> getUsersWithRole(User.ROLE role){
        List<String>logins = new ArrayList<>();
        for (Map.Entry<String, User> entry : users.entrySet()) {
            if(entry.getValue().getRole().equals(role)){
                logins.add(entry.getKey());
            }
        }
        return logins;
    }
}
