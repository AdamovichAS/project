package com.github.adamovichas.project.dao.repository;

import com.github.adamovichas.project.entity.EventStatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface EventStatisticRepository extends JpaRepository<EventStatisticEntity,Long> {
    boolean existsByEventId(@Param("eventId") Long eventId);
}
