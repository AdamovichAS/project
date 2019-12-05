package com.github.adamovichas.project.dao.repository;

import com.github.adamovichas.project.entity.LeagueEntity;
import com.github.adamovichas.project.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeagueRepository extends JpaRepository<LeagueEntity,Long> {
}
