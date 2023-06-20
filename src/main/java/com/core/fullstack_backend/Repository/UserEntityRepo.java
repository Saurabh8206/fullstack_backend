package com.core.fullstack_backend.Repository;

import com.core.fullstack_backend.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepo extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserName(String userName);
}
