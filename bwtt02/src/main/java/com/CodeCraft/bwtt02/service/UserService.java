package com.CodeCraft.bwtt02.service;

import com.CodeCraft.bwtt02.dto.UserDetailsDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDetailsDto createUser(UserDetailsDto userDetails);
    UserDetailsDto getUserBYId(UUID id);
    List<UserDetailsDto> getAllUsers();
    UserDetailsDto updateUser(UUID id, UserDetailsDto userDetails);
    void deleteUser (UUID id);
}
