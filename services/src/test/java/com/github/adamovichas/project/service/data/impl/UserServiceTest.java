package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.dao.impl.UserDao;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.model.dto.UserPassportDTO;
import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.model.user.passport.VereficationStatus;
import com.github.adamovichas.project.service.util.user.IUserUtil;
import com.github.adamovichas.project.service.util.user.passport.IUserPassportUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    public UserDao userDao;

    @Mock
    public IUserUtil userUtil;

    @Mock
    public IUserPassportUtil passportUtil;

    @InjectMocks
    public UserService userService;

    @BeforeEach
    public void initMock(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loginIsExist(){
        final UserDTO testUser = createTestUser();
        final String password = testUser.getPassword();
        when(userDao.getUserByLogin(testUser.getLogin())).thenReturn(testUser);
        assertNotNull(testUser);
        assertEquals(password,testUser.getPassword());
        boolean isExist = userService.loginIsExist(testUser.getLogin());
        assertTrue(isExist);
    }

    @Test
    void userIsExist(){
        final UserDTO testUser = createTestUser();
        when(userDao.getUserByLogin(testUser.getLogin())).thenReturn(testUser);
        boolean isExist = userService.userIsExist(testUser.getLogin(),testUser.getPassword());
        assertTrue(isExist);
    }

    @Test
    void addNewUser(){
        Map<String,String>userFields = new HashMap<>();
        userFields.put("login","login");
        userFields.put("password","password");
        UserDTO user = new UserDTO();
        user.setLogin("login");
        user.setPassword("password");
        when(userUtil.createUser(userFields)).thenReturn(user);
        userService.addNewUser(userFields);
        Mockito.verify(userDao,times(1)).addUser(user);
    }

    @Test
    void updateUser(){
        UserDTO testUser = createTestUser();
        String newPassword = "321";
        Role newRole = Role.BLOCKED;
        Map<String,String> userFieldsForUpd = new HashMap<>();
        userFieldsForUpd.put("password",newPassword);
        userFieldsForUpd.put("role",newRole.toString());
        UserDTO testUserAfterUpdate = createTestUser();
        testUserAfterUpdate.setPassword(newPassword);
        testUserAfterUpdate.setRole(newRole);
        when(userDao.getUserByLogin(testUser.getLogin())).thenReturn(testUser);
        when(userDao.getUserByLogin("returnFalse")).thenReturn(null);
        when(userDao.updateUser(testUser)).thenReturn(true);
        boolean trueResult = userService.updateUser(testUser.getLogin(), userFieldsForUpd);
        assertTrue(trueResult);
        verify(userUtil,times(1)).updateFields(testUser,userFieldsForUpd);
        final boolean falseResult = userService.updateUser("returnFalse", userFieldsForUpd);
        assertFalse(falseResult);
    }

    @Test
    void getUserByLogin(){
        String login = "login";
        UserDTO user = new UserDTO();
        user.setLogin(login);
        when(userDao.getUserByLogin(login)).thenReturn(user);
        userService.getUserByLogin(login);
        verify(userDao,times(1)).getUserByLogin(login);

    }


    @Test
    void getPassport(){
        final UserPassportDTO testPassport = createTestPassport();
        when(userDao.getPassport(testPassport.getUserLogin())).thenReturn(testPassport);
        userService.getPassport(testPassport.getUserLogin());
    }

    @Test
    void addUserPassport(){
        UserPassportDTO testPassport = createTestPassport();
        Map<String,String> passFieldsForUpd = new HashMap<>();
        when(passportUtil.createPassport(testPassport.getUserLogin(),passFieldsForUpd)).thenReturn(testPassport);
        when(userDao.addPassport(testPassport)).thenReturn(testPassport);
        userService.addUserPassport(testPassport.getUserLogin(),passFieldsForUpd);
        verify(passportUtil,times(1)).createPassport(testPassport.getUserLogin(),passFieldsForUpd);
        verify(userDao,times(1)).addPassport(testPassport);
    }

    @Test
    void updatePassport(){
        UserPassportDTO testPassport = createTestPassport();
        Map<String,String> passportFieldsForUpd = new HashMap<>();
        when(userDao.getPassport(testPassport.getUserLogin())).thenReturn(testPassport);
        when(userDao.updatePassport(testPassport)).thenReturn(testPassport);
        userService.updatePassport(testPassport.getUserLogin(),passportFieldsForUpd);
        verify(passportUtil,times(1)).updateFields(testPassport,passportFieldsForUpd);
    }

    @Test
    void verifyUser(){
        UserPassportDTO testPassport = createTestPassport();
        VereficationStatus newStatus = VereficationStatus.VEREF_PASSED;
        when(userDao.getPassport(testPassport.getUserLogin())).thenReturn(testPassport);
        when(userDao.getCashAccountByLogin(testPassport.getUserLogin())).thenReturn(null);
        userService.verifyUser(testPassport.getUserLogin(),newStatus);
        verify(userDao,times(1)).getPassport(testPassport.getUserLogin());
        verify(userDao,times(1)).updatePassport(testPassport);
        verify(userDao,times(1)).getCashAccountByLogin(testPassport.getUserLogin());
        verify(userDao,times(1)).addUserCashAccount(testPassport.getUserLogin());
    }


    UserDTO createTestUser(){
        UserDTO user = new UserDTO();
        user.setLogin("test");
        user.setPassword("123");
        user.setRole(Role.USER);
        return  user;
    }

    public UserPassportDTO createTestPassport() {
        UserDTO testUser = createTestUser();
        UserPassportDTO passportDTO = new UserPassportDTO();
        passportDTO.setUserLogin(testUser.getLogin());
        passportDTO.setPassSeries("MP");
        passportDTO.setLastName("Test");
        passportDTO.setFirstName("Test");
        passportDTO.setVereficationStatus(VereficationStatus.VEREF_WAITING);
        passportDTO.setPassFileName("test");
        return passportDTO;
    }


}
