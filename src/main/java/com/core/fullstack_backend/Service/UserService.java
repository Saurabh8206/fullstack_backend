package com.core.fullstack_backend.Service;

import com.core.fullstack_backend.Exception.ResourceNotFoundException;
import com.core.fullstack_backend.Model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserEntity createUser(UserEntity user);

    UserEntity updateUser(long id, UserEntity user) throws ResourceNotFoundException;

    void deleteUser(long id) throws ResourceNotFoundException;

    UserEntity getUserById(long id) throws ResourceNotFoundException;

    List<UserEntity> getAllUsers();

    Page<UserEntity> getAllUsersPaginated(Pageable pageable);

    UserEntity getUserByUserName(String userName) throws ResourceNotFoundException;
}

