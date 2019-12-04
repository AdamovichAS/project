package com.github.adamovichas.project.dao.repository;

import com.github.adamovichas.project.entity.BetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<BetEntity,Long> {
}
