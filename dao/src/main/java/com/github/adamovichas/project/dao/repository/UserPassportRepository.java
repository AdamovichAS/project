package com.github.adamovichas.project.dao.repository;

import com.github.adamovichas.project.entity.UserPassportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPassportRepository extends JpaRepository<UserPassportEntity,String> {
}
