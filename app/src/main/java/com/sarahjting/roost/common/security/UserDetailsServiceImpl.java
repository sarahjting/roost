package com.sarahjting.roost.common.security;

import com.sarahjting.roost.user.User;
import com.sarahjting.roost.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userService.findOneByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Invalid credentials given.");
        }

        return new UserDetailsAdapter(user.get());
    }
}
