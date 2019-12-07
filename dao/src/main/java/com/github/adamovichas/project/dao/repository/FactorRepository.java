package com.github.adamovichas.project.dao.repository;

import com.github.adamovichas.project.entity.FactorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FactorRepository extends JpaRepository<FactorEntity,Long> {
    List<FactorEntity> getAllByEventId(@Param("eventId") Long eventId);
}
