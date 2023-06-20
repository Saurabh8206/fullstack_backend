package com.core.fullstack_backend.ServiceImpl;

import com.core.fullstack_backend.Exception.ResourceNotFoundException;
import com.core.fullstack_backend.Model.UserEntity;
import com.core.fullstack_backend.Repository.UserEntityRepo;
import com.core.fullstack_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserEntityRepo userRepository;

    public UserServiceImpl(UserEntityRepo userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(UserEntity user) {

        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUser(long id, UserEntity user) throws ResourceNotFoundException {

        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        existingUser.setUserName(user.getUserName());
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setContactNo(user.getContactNo());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(long id) throws ResourceNotFoundException {
        UserEntity userForDelete = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(userForDelete);
    }


    @Override
    public UserEntity getUserById(long id) throws ResourceNotFoundException {

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public List<UserEntity> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public Page<UserEntity> getAllUsersPaginated(Pageable pageable) {

        return userRepository.findAll(pageable);
    }

    @Override
    public UserEntity getUserByUserName(String userName) throws ResourceNotFoundException {

        UserEntity userEntity = userRepository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + userName));
        return userEntity;
    }


}

