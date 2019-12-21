package com.github.adamovichas.project.dao;


import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.model.dto.UserPassportDTO;
import com.github.adamovichas.project.model.user.passport.VereficationStatus;

import java.util.List;

public interface IUserDao {
    boolean updateUser(UserDTO user);
    void addUser(UserDTO user);
    UserDTO getUserByLogin(String login);
    void blockUser(String login);

    /**
     *CashAccount
     * @return
     */
    CashAccountDTO addUserCashAccount(String login);
    CashAccountDTO getCashAccountByLogin(String login);
    CashAccountDTO updateCashAccountValue(CashAccountDTO cashAccountDTO);

    /**
     * UserPassport
     * @return
     */
    UserPassportDTO addPassport(UserPassportDTO userPassport);
    UserPassportDTO updatePassport(UserPassportDTO userPassport);
    UserPassportDTO getPassport(String login);
    Long getCountPassportPagesByVerificationStatusWaitingAndRoleUser();
    List<UserPassportDTO> getPassportOnPageByVerificationStatusWaitAndRoleUser(int page, int pageSize);
}
