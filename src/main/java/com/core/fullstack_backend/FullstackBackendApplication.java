package com.core.fullstack_backend;

import com.core.fullstack_backend.Model.UserEntity;
import com.core.fullstack_backend.Repository.UserEntityRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.List;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class FullstackBackendApplication {

    @Autowired

    private final UserEntityRepo userRepository;

    public FullstackBackendApplication(UserEntityRepo userRepository) {

        this.userRepository = userRepository;
    }


    public static void main(String[] args) {

        SpringApplication.run(FullstackBackendApplication.class, args);
    }

    @PostConstruct
    public void initializeSampleData() {

        UserEntity user = UserEntity.builder().userName("username").name("name").email("email@eamil.com").contactNo("964115436").build();
        UserEntity user1 = UserEntity.builder().userName("username").name("name").email("email@eamil.com").contactNo("964115436").build();
        UserEntity user2 = UserEntity.builder().userName("username").name("name").email("email@eamil.com").contactNo("964115436").build();
        UserEntity user3 = UserEntity.builder().userName("username").name("name").email("email@eamil.com").contactNo("964115436").build();

        userRepository.saveAll(List.of(user, user1, user2, user3));
        System.out.println("Sample data saved successfully!");
    }
}
