package com.github.adamovichas.project.util;

import com.github.adamovichas.project.entity.*;
import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.model.dto.*;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.view.BetView;


import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public abstract class EntityDtoViewConverter {

    public static UserEntity getEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setRole(userDTO.getRole());
        userEntity.setIsDeleted(userDTO.getIsDeleted());
        return userEntity;
    }

    public static UserDTO getDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(userEntity.getLogin());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setRole(userEntity.getRole());
        userDTO.setIsDeleted(userEntity.getIsDeleted());
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

    public static LeagueDTO getDTO(LeagueEntity leagueEntity) {
        return new LeagueDTO(leagueEntity.getId(), leagueEntity.getName());
    }

    public static TeamEntity getEntity(TeamDTO teamDTO) {
        return new TeamEntity(teamDTO.getName());
    }

    public static TeamDTO getDTO(TeamEntity teamEntity) {
        return new TeamDTO(teamEntity.getName());
    }

    public static EventEntity getEntity(EventDTO eventDTO) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(eventDTO.getId());
        eventEntity.setTeamOneId(eventDTO.getTeamOne());
        eventEntity.setTeamTwoId(eventDTO.getTeamTwo());
        eventEntity.setLeagueId(eventDTO.getLeagueId());
        eventEntity.setStartTime(eventDTO.getStartTime());
        eventEntity.setEndTime(eventDTO.getEndTime());
        eventEntity.setResultFactorId(eventDTO.getResultFactorId());
        if (nonNull(eventDTO.getFactors())) {
            List<FactorEntity> factorEntities = new ArrayList<>();
            for (FactorDTO dtoFactor : eventDTO.getFactors()) {
                factorEntities.add(getEntity(dtoFactor));
            }
            eventEntity.setFactors(factorEntities);
        }
        return eventEntity;
    }

    public static EventDTO getDTO(EventEntity eventEntity) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(eventEntity.getId());
        eventDTO.setTeamOne(eventEntity.getTeamOneId());
        eventDTO.setTeamTwo(eventEntity.getTeamTwoId());
        eventDTO.setLeagueId(eventEntity.getLeagueId());
        eventDTO.setStartTime(eventEntity.getStartTime());
        eventDTO.setEndTime(eventEntity.getEndTime());
        eventDTO.setResultFactorId(eventEntity.getResultFactorId());
        if (nonNull(eventEntity.getFactors())) {
            List<FactorDTO> factorsDTO = getFactorsDTO(eventEntity);
            eventDTO.setFactors(factorsDTO);
        }
        return eventDTO;
    }

    private static List<FactorDTO> getFactorsDTO(EventEntity eventEntity) {
        List<FactorDTO> factorsDTO = new ArrayList<>();
        for (FactorEntity entityFactor : eventEntity.getFactors()) {
            factorsDTO.add(getDTO(entityFactor));
        }
        return factorsDTO;
    }

//    public static EventDTO getView(EventEntity entity, String teamOneName, String teamTwoName){
//        EventDTO eventDTO = new EventDTO();
//        String teamOneName = null;
//        String teamTwoName = null;
//        for (TeamEntity team : entity.getTeams()) {
//            if(entity.getTeamOne().equals(team.getId())){
//                teamOneName = team.getName();
//            }else {
//                teamTwoName = team.getName();
//            }
//        }
//        String eventName = teamOneName + " - " + teamTwoName;
//        eventDTO.setName(eventName);
//        eventDTO.setId(entity.getId());
//        eventDTO.setStartTime(entity.getStartTime());
//        eventDTO.setEndTime(entity.getEndTime());
//        List<FactorDTO> factorsDTO = getFactorsDTO(entity);
//        eventDTO.setFactors(factorsDTO);
//        for (FactorEntity factor : entity.getFactors()) {
//            if(factor.getId().equals(entity.getResultFactorId())){
//                eventDTO.setResultFactorValue(factor.getValue());
//                break;
//            }
//        }
//        return eventDTO;
//    }

    public static BetEntity getEntity(BetDTO betDTO) {
        BetEntity betEntity = new BetEntity();
        betEntity.setId(betDTO.getId());
        betEntity.setFactorId(betDTO.getFactorId());
        betEntity.setMoney(betDTO.getMoney());
        betEntity.setUserLogin(betDTO.getUserLogin());
        return betEntity;
    }

    public static BetDTO getDTO(BetEntity betEntity) {
        BetDTO betDTO = new BetDTO();
        betDTO.setId(betEntity.getId());
        betDTO.setUserLogin(betEntity.getUserLogin());
        betDTO.setFactorId(betEntity.getFactorId());
        betDTO.setMoney(betEntity.getMoney());
        return betDTO;
    }

    public static CashAccountDTO getDTO(CashAccountEntity cashAccountEntity) {
        CashAccountDTO cashAccountDTO = new CashAccountDTO();
        cashAccountDTO.setLogin(cashAccountEntity.getLogin());
        cashAccountDTO.setValue(cashAccountEntity.getValue());
        return cashAccountDTO;
    }

    public static CashAccountEntity getEntity(CashAccountDTO cashAccountDTO) {
        CashAccountEntity cashAccountEntity = new CashAccountEntity();
        cashAccountEntity.setLogin(cashAccountDTO.getLogin());
        cashAccountEntity.setValue(cashAccountDTO.getValue());
        return cashAccountEntity;
    }

    public static BetView getView(BetEntity betEntity) {
        BetView betView = new BetView();
        betView.setId(betEntity.getId());
        betView.setEvent(betEntity.getFactor().getEvent().getName());
        FactorDTO factorDTO = getDTO(betEntity.getFactor());
        betView.setFactor(factorDTO);
        betView.setLogin(betEntity.getUserLogin());
        betView.setMoney(betEntity.getMoney());
        return betView;
    }
}
