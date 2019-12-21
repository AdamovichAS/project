package com.github.adamovichas.project.dao.repository;

import com.github.adamovichas.project.entity.UserPassportEntity;
import com.github.adamovichas.project.model.user.passport.VereficationStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserPassportRepository extends JpaRepository<UserPassportEntity,String> {
    @Query(value = "FROM UserPassportEntity p WHERE p.userEntity.role = 'USER' and p.vereficationStatus= 'VEREF_WAITING'")
    List<UserPassportEntity> getPassportsOnPageByVerificationStatusWaitingAndRoleUser(Pageable page);

    @Query(value = "SELECT count(*) FROM user_passport " +
            "left join user u on user_passport.user_login = u.login " +
            "WHERE user_passport.verification_status = 'VEREF_WAITING' and u.role = 'USER';",
            nativeQuery = true)
    Long getCountPassportsByVereficationStatusWiatAndRoleUser();
}

