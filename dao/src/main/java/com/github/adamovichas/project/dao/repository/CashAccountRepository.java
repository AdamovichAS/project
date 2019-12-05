package com.github.adamovichas.project.dao.repository;

import com.github.adamovichas.project.entity.CashAccountEntity;
import com.github.adamovichas.project.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CashAccountRepository extends JpaRepository<CashAccountEntity, String> {
    @Query("FROM UserEntity u WHERE u.login= :login")
    UserEntity getUserByLogin(@Param("login") String login);
}
