package com.github.adamovichas.project.dao;

import com.github.adamovichas.project.entity.UserPassportEntity;
import com.github.adamovichas.project.model.dto.UserPassportDTO;

public interface IUserPassportDao {

    boolean save(UserPassportDTO userPassport);
    UserPassportDTO update(UserPassportDTO userPassport);
    UserPassportDTO getPassport(String login);
    boolean isExist(String login);
}
