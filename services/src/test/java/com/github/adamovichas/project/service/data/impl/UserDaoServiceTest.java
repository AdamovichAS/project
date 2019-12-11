package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.dao.impl.UserDao;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.service.util.user.IUserUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDaoServiceTest {

    @Mock
    public UserDao userDao;

    @Mock
    public IUserUtil userUtil;

    @InjectMocks
    public UserService dataUserService;

    @BeforeEach
    public void initMock(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loginIsExist(){
        final UserDTO testUser = createTestUser();
        final String password = testUser.getPassword();
        when(userDao.getUserByLogin(testUser.getLogin())).thenReturn(testUser);
        assertNotNull(testUser);
        assertEquals(password,testUser.getPassword());
        boolean isExist = dataUserService.loginIsExist(testUser.getLogin());
        assertTrue(isExist);
    }

    @Test
    public void userIsExist(){
        final UserDTO testUser = createTestUser();
        when(userDao.getUserByLogin(testUser.getLogin())).thenReturn(testUser);
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
        when(userUtil.createUser(userFields)).thenReturn(user);
        dataUserService.addNewUser(userFields);
        Mockito.verify(userDao,times(1)).addUser(user);
    }

    @Test
    public void getUserByLogin(){
        String login = "login";
        UserDTO user = new UserDTO();
        user.setLogin(login);
        when(userDao.getUserByLogin(login)).thenReturn(user);
        dataUserService.getUserByLogin(login);
        verify(userDao,times(1)).getUserByLogin(login);

    }


    UserDTO createTestUser(){
        UserDTO user = new UserDTO();
        user.setLogin("test");
        user.setPassword("123");
        user.setRole(Role.USER_NOT_VER);
        return  user;
    }
}
