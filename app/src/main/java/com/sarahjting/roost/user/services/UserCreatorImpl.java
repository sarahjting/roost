package com.sarahjting.roost.user.services;

import com.sarahjting.roost.user.User;
import com.sarahjting.roost.user.UserDto;
import com.sarahjting.roost.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserCreatorImpl implements UserCreator {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public User execute(UserDto userDto) {
        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setCreatedAt(LocalDateTime.now());

        return userRepository.save(newUser);
    }
}
