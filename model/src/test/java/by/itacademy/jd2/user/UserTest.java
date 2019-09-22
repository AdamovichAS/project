package by.itacademy.jd2.user;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    private static User user = new User("Anton","123",10, User.ROLE.ADMIN);

    @Test
    public void getLoginTest(){
        assertEquals("Anton",user.getLogin());
    }

    @Test
    public void getPasswordTest(){
        assertEquals("123",user.getPassword());
    }

    @Test
    public void getMoneyTest(){
        assertEquals(10,user.getMoney());
    }

    @Test
    public void getRoleTest(){
        assertEquals(User.ROLE.ADMIN,user.getRole());
    }
}
