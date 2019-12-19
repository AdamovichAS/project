package com.github.adamovichas.project.service.util.user.passport;

import com.github.adamovichas.project.model.dto.UserPassportDTO;

import java.util.Map;

public interface IUserPassportUtil {
    UserPassportDTO createPassport(String login,Map<String,String> passportFieldsAndValues);
    void updateFields(UserPassportDTO passportForUpdate, Map<String,String> fieldsForUpdate);
}
