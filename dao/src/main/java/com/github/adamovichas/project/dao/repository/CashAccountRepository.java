package com.github.adamovichas.project.dao.repository;

import com.github.adamovichas.project.entity.CashAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashAccountRepository extends JpaRepository<CashAccountEntity, String> {
}
