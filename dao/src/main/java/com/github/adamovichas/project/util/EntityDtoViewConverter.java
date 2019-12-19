package com.github.adamovichas.project.util;

import com.github.adamovichas.project.entity.*;
import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.model.bet.Status;
import com.github.adamovichas.project.model.dto.*;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.view.BetView;
import com.github.adamovichas.project.model.view.EventView;


import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public abstract class EntityDtoViewConverter {

    public static UserEntity getEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setRole(userDTO.getRole());
        return userEntity;
    }

    public static UserDTO getDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(userEntity.getLogin());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setRole(userEntity.getRole());
        return userDTO;
    }

    public static FactorEntity getEntity(FactorDTO factorDTO) {
        FactorEntity factorEntity = new FactorEntity();
        factorEntity.setName(factorDTO.getName());
        factorEntity.setValue(factorDTO.getValue());
//        factorEntity.setEventId(factorDTO.getEventId());
        factorEntity.setId(factorDTO.getId());
        return factorEntity;

    }

    public static FactorDTO getDTO(FactorEntity factorEntity) {
        FactorDTO factorDTO = new FactorDTO();
        factorDTO.setId(factorEntity.getId());
        factorDTO.setName(factorEntity.getName());
        factorDTO.setValue(factorEntity.getValue());
        factorDTO.setEventId(factorEntity.getEvent().getId());
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
//        if (nonNull(eventDTO.getFactors())) {
//            List<FactorEntity> factorEntities = new ArrayList<>();
//            for (FactorDTO dtoFactor : eventDTO.getFactors()) {
//                FactorEntity entity = getEntity(dtoFactor);
//                factorEntities.add(entity);
//                entity.setEvent(eventEntity);
//            }
//            eventEntity.setFactors(factorEntities);
//        }
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
//        if (nonNull(eventEntity.getFactors())) {
//            List<FactorDTO> factorsDTO = getFactorsDTO(eventEntity);
//            eventDTO.setFactors(factorsDTO);
//        }
        return eventDTO;
    }

    public static EventView getView(EventEntity eventEntity){
        EventView eventView = new EventView();
        eventView.setId(eventEntity.getId());
        eventView.setTeamTwo(eventEntity.getTeamTwoId());
        eventView.setTeamOne(eventEntity.getTeamOneId());
        eventView.setLeagueId(eventEntity.getLeagueId());
        eventView.setStartTime(eventEntity.getStartTime());
        eventView.setEndTime(eventEntity.getEndTime());
        eventView.setResultFactorId(eventEntity.getResultFactorId());
        if(nonNull(eventEntity.getStatistic())) {
            eventView.setStatistic(getDTO(eventEntity.getStatistic()));
        }
        if (nonNull(eventEntity.getFactors())) {
            List<FactorDTO> factorsDTO = new ArrayList<>();
            for (FactorEntity entityFactor : eventEntity.getFactors()) {
                factorsDTO.add(getDTO(entityFactor));
            }
            eventView.setFactors(factorsDTO);
        }
        return eventView;
    }

//    private static List<FactorDTO> getFactorsDTO(EventEntity eventEntity) {
//        List<FactorDTO> factorsDTO = new ArrayList<>();
//        for (FactorEntity entityFactor : eventEntity.getFactors()) {
//            factorsDTO.add(getDTO(entityFactor));
//        }
//        return factorsDTO;
//    }

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
        if(betDTO.getStatus() == null){
            betEntity.setStatus(Status.RUN_TIME);
        }else {
            betEntity.setStatus(betDTO.getStatus());
        }
        return betEntity;
    }

    public static BetDTO getDTO(BetEntity betEntity) {
        BetDTO betDTO = new BetDTO();
        betDTO.setId(betEntity.getId());
        betDTO.setUserLogin(betEntity.getUserLogin());
        betDTO.setFactorId(betEntity.getFactorId());
        betDTO.setMoney(betEntity.getMoney());
        betDTO.setStatus(betEntity.getStatus());
        return betDTO;
    }

    public static CashAccountDTO getDTO(CashAccountEntity cashAccountEntity) {
        CashAccountDTO cashAccountDTO = new CashAccountDTO();
        cashAccountDTO.setLogin(cashAccountEntity.getUserLogin());
        cashAccountDTO.setValue(cashAccountEntity.getValue());
        return cashAccountDTO;
    }

    public static CashAccountEntity getEntity(CashAccountDTO cashAccountDTO) {
        CashAccountEntity cashAccountEntity = new CashAccountEntity();
        cashAccountEntity.setUserLogin(cashAccountDTO.getLogin());
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
        betView.setStatus(betEntity.getStatus());
        return betView;
    }

    public static AppCashAccountEntity getEntity(AppCashAccountDTO dto){
        AppCashAccountEntity entity = new AppCashAccountEntity();
        entity.setId(dto.getId());
        entity.setBalance(dto.getBalance());
        return entity;
    }

    public static AppCashAccountDTO getDTO(AppCashAccountEntity entity){
        AppCashAccountDTO dto = new AppCashAccountDTO();
        dto.setId(entity.getId());
        dto.setBalance(entity.getBalance());
        return dto;
    }

    public static UserPassportDTO getDTO(UserPassportEntity entity){
        UserPassportDTO dto = new UserPassportDTO();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPassSeries(entity.getPassSeries());
        dto.setUserLogin(entity.getUserLogin());
        dto.setPassFileName(entity.getPassFileName());
        dto.setVereficationStatus(entity.getVereficationStatus());
        return dto;
    }

    public static UserPassportEntity getEntity(UserPassportDTO dto){
        UserPassportEntity entity = new UserPassportEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setUserLogin(dto.getUserLogin());
        entity.setPassSeries(dto.getPassSeries());
        entity.setPassFileName(dto.getPassFileName());
        entity.setVereficationStatus(dto.getVereficationStatus());
        return entity;
    }

    public static EventStatisticDTO getDTO(EventStatisticEntity entity){
        EventStatisticDTO dto = new EventStatisticDTO();
 //       dto.setId(entity.getId());
        dto.setEventId(entity.getEventId());
        dto.setTeamOneGoals(entity.getTeamOneGoals());
        dto.setTeamTwoGoals(entity.getTeamTwoGoals());
        return dto;
    }

    public static EventStatisticEntity getEntity(EventStatisticDTO dto){
        EventStatisticEntity entity = new EventStatisticEntity();
 //       entity.setId(dto.getId());
        entity.setEventId(dto.getEventId());
        entity.setTeamOneGoals(dto.getTeamOneGoals());
        entity.setTeamTwoGoals(dto.getTeamTwoGoals());
        return entity;
    }
}
