package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.dao.ILeagueDao;
import com.github.adamovichas.project.dao.repository.LeagueRepository;
import com.github.adamovichas.project.dao.repository.TeamRepository;
import com.github.adamovichas.project.entity.LeagueEntity;
import com.github.adamovichas.project.entity.TeamEntity;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;
import com.github.adamovichas.project.util.EntityDtoViewConverter;

import java.util.ArrayList;
import java.util.List;

public class LeagueDao implements ILeagueDao {

    private final LeagueRepository leagueRepository;
    private final TeamRepository teamRepository;

    public LeagueDao(LeagueRepository leagueRepository, TeamRepository teamRepository) {
        this.leagueRepository = leagueRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<LeagueDTO> getAllLeagues() {
        List<LeagueEntity> entities = leagueRepository.findAll();
        List<LeagueDTO>dtos = new ArrayList<>();
        for (LeagueEntity entity : entities) {
            dtos.add(EntityDtoViewConverter.getDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<TeamDTO> getAllTeamsByLeague(Long idLeague) {
//        LeagueEntity leagueEntity = leagueRepository.getOne(idLeague);
//        List<TeamEntity> entityTeams = leagueEntity.getTeams();
        final List<TeamEntity> teams = teamRepository.getAllTeamsByLeague(idLeague);
        List<TeamDTO> teamDTOS = new ArrayList<>();
        for (TeamEntity team : teams) {
            teamDTOS.add(EntityDtoViewConverter.getDTO(team));
        }
        return teamDTOS;
    }
}
