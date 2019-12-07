package com.github.adamovichas.project.dao;


import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.model.dto.UserPassportDTO;

public interface IUserDao {
    boolean updateUser(UserDTO user);
    void addUser(UserDTO user);
    UserDTO getUserByLogin(String login);
    void block(String login);

    /**
     *CashAccount
     */
    void addUserCashAccount(String login);
    CashAccountDTO getCashAccountByLogin(String login);
    CashAccountDTO updateCashAccountValue(CashAccountDTO cashAccountDTO);

    /**
     * UserPassport
     */
    void addPassport(UserPassportDTO userPassport);
    UserPassportDTO updatePassport(UserPassportDTO userPassport);
    UserPassportDTO getPassport(String login);
}
