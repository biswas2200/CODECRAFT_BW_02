package com.CodeCraft.bwtt02.service.implementation;

import com.CodeCraft.bwtt02.dto.UserDetailsDto;
import com.CodeCraft.bwtt02.exception.UserNotFoundException;
import com.CodeCraft.bwtt02.model.UserDetails;
import com.CodeCraft.bwtt02.repository.UserRepository;
import com.CodeCraft.bwtt02.service.UserService;
import com.CodeCraft.bwtt02.utility.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailsDto createUser(UserDetailsDto dto) {
        UserDetails user = UserMapper.toEntity(dto);
        if (user.getId() == null)
            user.setId(UUID.randomUUID());
        UserDetails savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    @Override
    public UserDetailsDto getUserBYId(UUID id) {
        UserDetails user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User id not found " + id));
        return UserMapper.toDto(user);
    }

    @Override
    public List<UserDetailsDto> getAllUsers() {
        List<UserDetails> user = userRepository.findAll();
        return user.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailsDto updateUser(UUID id, UserDetailsDto userDto) {
        UserDetails existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User id not found "+id));
        if (userDto.getName() != null)
            existingUser.setName(userDto.getName());
        if (userDto.getEmail() != null)
            existingUser.setEmail(userDto.getEmail());
        if (userDto.getAge() != 0)
            existingUser.setAge(userDto.getAge());
        UserDetails updateUserDetails = userRepository.save(existingUser);
        return UserMapper.toDto(updateUserDetails);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User id not found " + id));
        userRepository.deleteById(id);
    }
}
