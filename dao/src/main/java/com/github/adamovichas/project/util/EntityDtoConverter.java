package com.github.adamovichas.project.util;

import com.github.adamovichas.project.entity.*;
import com.github.adamovichas.project.model.dto.*;
import com.github.adamovichas.project.model.factor.FactorDTO;

import java.util.ArrayList;
import java.util.List;

public class EntityDtoConverter {

    public static UserEntity getEntity(UserDTO userDTO) {
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

    public static UserDTO getDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(userEntity.getLogin());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPhone(userEntity.getPhone());
        userDTO.setAge(userEntity.getAge());
        userDTO.setCountry(userEntity.getCountry());
        userDTO.setRole(userEntity.getRole());
        return userDTO;
    }

    public static FactorEntity getEntity(FactorDTO factorDTO) {
        FactorEntity factorEntity = new FactorEntity();
        factorEntity.setName(factorDTO.getName());
        factorEntity.setValue(factorDTO.getValue());
        factorEntity.setEventID(factorDTO.getEventID());
        factorEntity.setId(factorDTO.getId());
        return factorEntity;

    }

    public static FactorDTO getDTO(FactorEntity factorEntity) {
        FactorDTO factorDTO = new FactorDTO();
        factorDTO.setId(factorEntity.getId());
        factorDTO.setName(factorEntity.getName());
        factorDTO.setValue(factorEntity.getValue());
        factorDTO.setEventID(factorEntity.getEventID());
        return factorDTO;
    }

    public static LeagueEntity getEntity(LeagueDTO leagueDTO) {
        return new LeagueEntity(leagueDTO.getId(), leagueDTO.getName());
    }

    public static LeagueDTO getDTO(LeagueEntity leagueEntity){
        return new LeagueDTO(leagueEntity.getId(),leagueEntity.getName());
    }

    public static TeamEntity getEntity(TeamDTO teamDTO){
        return new TeamEntity(teamDTO.getId(),teamDTO.getName());
    }

    public static TeamDTO getDTO(TeamEntity teamEntity){
        return new TeamDTO(teamEntity.getId(),teamEntity.getName());
    }

    public static EventEntity getEntity(EventDTO eventDTO){
        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(eventDTO.getId());
        eventEntity.setTeamOneId(eventDTO.getTeamOneId());
        eventEntity.setTeamTwoId(eventDTO.getTeamTwoId());
        eventEntity.setStartTime(eventDTO.getStartTime());
        eventEntity.setResultFactorId(eventDTO.getResultFactorId());
        List<FactorEntity>factorEntities = new ArrayList<>();
        for (FactorDTO dtoFactor : eventDTO.getFactors()) {
            factorEntities.add(getEntity(dtoFactor));
        }
        eventEntity.setFactors(factorEntities);
        return eventEntity;
    }

    public static EventDTO getDTO(EventEntity eventEntity){
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(eventEntity.getId());
        eventDTO.setTeamOneId(eventEntity.getTeamOneId());
        eventDTO.setTeamTwoId(eventEntity.getTeamTwoId());
        eventDTO.setStartTime(eventEntity.getStartTime());
        eventDTO.setEndTime(eventEntity.getEndTime());
        eventDTO.setResultFactorId(eventEntity.getResultFactorId());
        List<FactorDTO>factorsDTO = new ArrayList<>();
        for (FactorEntity entityFactor : eventEntity.getFactors()) {
            factorsDTO.add(getDTO(entityFactor));
        }
        eventDTO.setFactors(factorsDTO);
        return eventDTO;
    }

    public static BetEntity getEntity(BetDTO betDTO){
        BetEntity betEntity = new BetEntity();
        betEntity.setId(betDTO.getId());
        betEntity.setFactorId(betDTO.getFactorId());
        betEntity.setMoney(betDTO.getMoney());
        betEntity.setUserLogin(betDTO.getUserLogin());
        return betEntity;
    }

    public static BetDTO getDTO(BetEntity betEntity){
        BetDTO betDTO = new BetDTO();
        betDTO.setId(betEntity.getId());
        betDTO.setUserLogin(betEntity.getUserLogin());
        betDTO.setFactorId(betEntity.getFactorId());
        betDTO.setMoney(betEntity.getMoney());
        return betDTO;
    }
}
