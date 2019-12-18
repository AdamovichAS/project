package com.github.adamovichas.project.dao.repository;

import com.github.adamovichas.project.entity.EventEntity;
import com.github.adamovichas.project.entity.LeagueEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

//    @Query("FROM LeagueEntity l where l.id= :leagueId")
//    LeagueEntity getLeague(@Param("leagueId") Long leagueId);

    //    @Query("FROM EventEntity e WHERE e.teamOneId= :teamOne AND e.teamTwoId= :teamTwo and e.startTime= :startTime")
    boolean existsByTeamOneIdAndTeamTwoIdAndStartTime(@Param("teamOneId") String teamOneId,
                                                      @Param("teamTwoId") String teamTwoId,
                                                      @Param("startTime") Timestamp startTime);
    @Query(value = "SELECT count(*) from event where event.result is null",nativeQuery = true)
    Long getCountEventsByResultFactorIdIsNull();

    List<EventEntity> getAllByResultFactorIdIsNull();
    List<EventEntity> getAllByResultFactorIdIsNull(Pageable page);
    List<EventEntity> getAllByResultFactorIdIsNotNull(Pageable page);
}