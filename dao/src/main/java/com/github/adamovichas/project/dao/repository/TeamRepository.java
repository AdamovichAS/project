package com.github.adamovichas.project.dao.repository;

import com.github.adamovichas.project.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<TeamEntity,String> {
    @Query(value = "SELECT ltd.team as name FROM league_team_dependency ltd WHERE ltd.league_id= :leagueId", nativeQuery = true)
    List<TeamEntity> getAllTeamsByLeague(@Param("leagueId") Long leagueId);
}
