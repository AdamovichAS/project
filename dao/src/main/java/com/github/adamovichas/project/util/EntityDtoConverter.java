package com.github.adamovichas.project.util;

import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.model.dto.UserDTO;

public class EntityDtoConverter {

    public static UserEntity getEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setAge(userDTO.getAge());
        userEntity.setCountry(userDTO.getCountry());
        userEntity.setRole(userDTO.getRole());
        return userEntity;
    }

    public static UserDTO getDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(userEntity.getLogin());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setPhone(userEntity.getPhone());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setAge(userEntity.getAge());
        userDTO.setCountry(userEntity.getCountry());
        userDTO.setRole(userEntity.getRole());
        return userDTO;
    }
}
