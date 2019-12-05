package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.dao.ILeagueDao;
import com.github.adamovichas.project.dao.repository.LeagueRepository;
import com.github.adamovichas.project.entity.LeagueEntity;
import com.github.adamovichas.project.entity.TeamEntity;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;
import com.github.adamovichas.project.util.EntityDtoViewConverter;

import java.util.ArrayList;
import java.util.List;

public class LeagueDao implements ILeagueDao {

    private final LeagueRepository repository;

    public LeagueDao(LeagueRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<LeagueDTO> getAllLeagues() {
        List<LeagueEntity> entities = repository.findAll();
        List<LeagueDTO>dtos = new ArrayList<>();
        for (LeagueEntity entity : entities) {
            dtos.add(EntityDtoViewConverter.getDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<TeamDTO> getAllTeamsByLeague(Long idLeague) {
        LeagueEntity leagueEntity = repository.findById(idLeague).get();
        List<TeamEntity> entityTeams = leagueEntity.getTeams();
        List<TeamDTO> teamDTOS = new ArrayList<>();
        for (TeamEntity team : entityTeams) {
            teamDTOS.add(EntityDtoViewConverter.getDTO(team));
        }
        return teamDTOS;
    }
}
