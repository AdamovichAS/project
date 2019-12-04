package com.github.adamovichas.project.dao.repository;

import com.github.adamovichas.project.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
