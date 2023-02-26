package com.sarahjting.roost.user.services;

import com.sarahjting.roost.user.User;
import com.sarahjting.roost.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserPasswordUpdaterImpl implements UserPasswordUpdater {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User execute(User user, String oldPassword, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }
}
