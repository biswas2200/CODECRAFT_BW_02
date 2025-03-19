package com.CodeCraft.bwtt02.controller;

import com.CodeCraft.bwtt02.dto.UserDetailsDto;
import com.CodeCraft.bwtt02.exception.BadRequestException;
import com.CodeCraft.bwtt02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDetailsDto> createUser(@RequestBody UserDetailsDto dto) {
        if (dto.getEmail() == null || !dto.getEmail().contains("@"))
            throw new BadRequestException("Invalid email address");
        if (dto.getAge() > 90)
            throw new BadRequestException("Age above 90 years");
        if (dto.getId() == null)
            dto.setId(UUID.randomUUID());//Generate a new UUID if necessary
        UserDetailsDto createUser = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);//201
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDto> getUserById(@PathVariable UUID id) {
        UserDetailsDto user = userService.getUserBYId(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);//200
    }

    @GetMapping
    public ResponseEntity<List<UserDetailsDto>> getAllUsers() {
        List<UserDetailsDto> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailsDto> updateUser(@PathVariable UUID id,
                                                     @RequestBody UserDetailsDto dto) {
        if (dto.getEmail() == null || !dto.getEmail().contains("@"))
            throw new BadRequestException("Invalid email address");
        if (dto.getAge() > 90)
            throw new BadRequestException("Age above 90 years");
        UserDetailsDto updatedUser = userService.updateUser(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();//204
    }
}
