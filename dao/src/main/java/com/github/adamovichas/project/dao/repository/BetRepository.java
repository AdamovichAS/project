package com.github.adamovichas.project.dao.repository;

import com.github.adamovichas.project.entity.BetEntity;
import com.github.adamovichas.project.model.bet.Status;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BetRepository extends JpaRepository<BetEntity,Long> {
 //   @Query("FROM BetEntity b WHERE b.userLogin= :login AND b.status= 'RUN_TIME'")
    List<BetEntity>getAllByUserLoginAndStatus(@Param("userLogin")String userLogin,
                                              @Param("status") Status status);

    List<BetEntity>getAllByUserLoginAndStatus(@Param("login")String userLogin,
                                         @Param("status") Status status,
                                         Pageable page);
}
