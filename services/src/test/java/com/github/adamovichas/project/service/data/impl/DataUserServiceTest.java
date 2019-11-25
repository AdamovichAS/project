package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.dao.impl.DataUser;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.service.user.UserCreater;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class DataUserServiceTest {

    @Mock
    public DataUser dataUser;

    @Mock
    public UserCreater userCreater;

    @InjectMocks
    public DataUserService dataUserService;

    @BeforeEach
    public void initMock(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loginIsExist(){
        final UserDTO testUser = createTestUser();
        final String password = testUser.getPassword();
        when(dataUser.getUserByLogin(testUser.getLogin())).thenReturn(testUser);
        assertTrue(testUser !=null);
        assertEquals(password,testUser.getPassword());
        boolean isExist = dataUserService.loginIsExist(testUser.getLogin());
        assertTrue(isExist);
    }

    @Test
    public void userIsExist(){
        final UserDTO testUser = createTestUser();
        when(dataUser.getUserByLogin(testUser.getLogin())).thenReturn(testUser);
        boolean isExist = dataUserService.userIsExist(testUser.getLogin(),testUser.getPassword());
        assertTrue(isExist);
    }

    @Test
    public void addNewUser(){
        Map<String,String>userFields = new HashMap<>();
        userFields.put("login","login");
        userFields.put("password","password");
        UserDTO user = new UserDTO();
        user.setLogin("login");
        user.setPassword("password");
        when(userCreater.createUser(userFields)).thenReturn(user);
        when(dataUser.addUser(user)).thenReturn(true);
        boolean addNewUser = dataUserService.addNewUser(userFields);
        assertTrue(addNewUser);
    }

    @Test
    public void getUserByLogin(){
        String login = "login";
        UserDTO user = new UserDTO();
        user.setLogin(login);
        when(dataUser.getUserByLogin(login)).thenReturn(user);
        dataUserService.getUserByLogin(login);
        verify(dataUser,times(1)).getUserByLogin(login);

    }


    UserDTO createTestUser(){
        UserDTO user = new UserDTO();
        user.setLogin("test");
        user.setPassword("123");
        user.setFirstName("name");
        user.setLastName("lastName");
        user.setPhone("567");
        user.setEmail("mail");
        user.setAge(18);
        user.setCountry("bel");
        user.setRole(Role.USER_VER);
        return  user;
    }
}
