package com.github.adamovichas.project.service.data;

import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.model.dto.UserPassportDTO;
import com.github.adamovichas.project.model.user.passport.VereficationStatus;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;

public interface IUserService {

    /**
     *User
     */

    boolean loginIsExist(String login);
    String addNewUser(Map<String,String> userFieldsAndValues);
    boolean userIsExist(String login, String password);
    boolean updateUser(String login, Map<String, String> usersFieldsForUpdateWithLogin);
    UserDTO getUserByLogin(String login);

    /**
     * UserPassport
     * @return
     */

    UserPassportDTO addUserPassport(String login, Map<String,String> passportFieldsAndValues);
    UserPassportDTO updatePassport(String login, Map<String,String> passportFieldsForUpdate);


    void verifyUser(String login, VereficationStatus newStatus);

    UserPassportDTO getPassport(String login);
    Long getPassportMaxPagesByVerificationStatusWaiting();
    List<UserPassportDTO> getPassportOnPageByVerificationStatusWaitAndRoleUser(int page);

    /**
     * UserCashAccount
     * @return
     */

//    CashAccountDTO addUserCashAccount(String login);
//    CashAccountDTO getCashAccountByLogin(String login);
//    CashAccountDTO updateCashAccountValue(double difference);

//    CashAccountDTO updateCashAccountValue(CashAccountDTO cashAccountDTO);
}
