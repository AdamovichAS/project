package com.github.adamovichas.project.service.util.user;

import com.github.adamovichas.project.model.dto.UserDTO;

import java.util.Map;

public interface IUserUtil {
    UserDTO createUser(Map<String,String> userFieldsAndValues);
    void updateFields(UserDTO userForUpdate, Map<String,String> fieldsForUpdate);
}
