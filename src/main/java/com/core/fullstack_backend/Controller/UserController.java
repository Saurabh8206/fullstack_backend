package com.core.fullstack_backend.Controller;

import com.core.fullstack_backend.Exception.ResourceNotFoundException;
import com.core.fullstack_backend.Model.UserEntity;
import com.core.fullstack_backend.Service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;

    @Value("client.authorization.id")
    private String sessionId;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user/create")
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserEntity user,
                                                 @RequestHeader(value = "sessionId", required = true) String sessionId) {
        UserEntity createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/users/update/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable long id, @Valid @RequestBody UserEntity user,
                                                 @RequestHeader(value = "sessionId", required = true) String sessionId) {

        try {
            UserEntity updatedUser = userService.updateUser(id, user);

            log.info("User with ID " + id + " updated successfully");
            return ResponseEntity.ok().body(updatedUser);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id,
                                             @RequestHeader(value = "sessionId", required = true) String sessionId) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User with id " + id + " deleted successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/users/fetchById/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable long id,
                                                  @RequestHeader(value = "sessionId", required = true) String sessionId) {
        try {
            UserEntity user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping("/users/get-all")
    public ResponseEntity<List<UserEntity>> getAllUsers(@RequestHeader(value = "sessionId", required = true) String sessionId) {
        List<UserEntity> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/get-all-paginated")
    public ResponseEntity<Page<UserEntity>> getAllUsersPaginated(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestHeader(value = "sessionId", required = true) String sessionId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> users = userService.getAllUsersPaginated(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/fetchByUserName/{userName}")
    public ResponseEntity<UserEntity> getUserByUserName(@PathVariable String userName,
                                                        @RequestHeader(value = "sessionId", required = true) String sessionId) throws ResourceNotFoundException {
        try {
            UserEntity user = userService.getUserByUserName(userName);
            return ResponseEntity.ok(user);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

