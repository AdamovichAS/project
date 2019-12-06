package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.dao.IUserPassportDao;
import com.github.adamovichas.project.dao.repository.UserPassportRepository;
import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.entity.UserPassportEntity;
import com.github.adamovichas.project.model.dto.UserPassportDTO;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import org.hibernate.Session;

import javax.persistence.PersistenceContext;

public class UserPassportDao implements IUserPassportDao {

    private final UserPassportRepository repository;

    @PersistenceContext
    private Session session;

    public UserPassportDao(UserPassportRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean addPassport(UserPassportDTO userPassport) {
        UserPassportEntity passportEntity = EntityDtoViewConverter.getEntity(userPassport);
        UserEntity userEntity = session.find(UserEntity.class, userPassport.getUserLogin());
        passportEntity.setUserEntity(userEntity);
        userEntity.setUserPassportEntity(passportEntity);
        repository.saveAndFlush(passportEntity);
        return true;
    }

    @Override
    public UserPassportDTO update(UserPassportDTO userPassport) {
        UserPassportEntity passportEntity = repository.getOne(userPassport.getUserLogin());
        setDtoFieldsToEntity(userPassport,passportEntity);
        return EntityDtoViewConverter.getDTO(passportEntity);
    }

    @Override
    public UserPassportDTO getPassport(String login) {
        UserPassportEntity passportEntity = repository.findById(login).get();
        return EntityDtoViewConverter.getDTO(passportEntity);
    }

    @Override
    public boolean isExist(String login) {
        return repository.existsById(login);
    }

    private void setDtoFieldsToEntity(UserPassportDTO dto, UserPassportEntity entity){
        entity.setAddress(dto.getAddress());
        entity.setBirthDay(dto.getBirthDay());
        entity.setFirstName(dto.getFirstName());
        entity.setCountry(dto.getCountry());
        entity.setPassEndDate(dto.getPassEndDate());
        entity.setLastName(dto.getLastName());
        entity.setPassIssueDate(dto.getPassIssueDate());
        entity.setPassNumber(dto.getPassNumber());
        entity.setUserLogin(dto.getUserLogin());
        entity.setPassSeries(dto.getPassSeries());
    }
}
