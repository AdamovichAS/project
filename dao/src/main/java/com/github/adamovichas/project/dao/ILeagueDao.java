package com.github.adamovichas.project.dao;

import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;

import java.util.List;

public interface ILeagueDao {
    List<LeagueDTO> getAllLeagues();
    List<TeamDTO> getAllTeamsByLeague(Long idLeague);
}
