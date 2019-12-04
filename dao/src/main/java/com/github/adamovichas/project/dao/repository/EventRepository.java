package com.github.adamovichas.project.dao.repository;

import com.github.adamovichas.project.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
}
