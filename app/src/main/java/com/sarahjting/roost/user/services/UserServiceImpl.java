package com.sarahjting.roost.user.services;

import com.sarahjting.roost.user.User;
import com.sarahjting.roost.user.UserRepository;
import com.sarahjting.roost.user.projections.UserBasicProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> findOneByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Slice<UserBasicProjection> findBasicSlice(Pageable pageable) {
        return userRepository.findBasicSliceByOrderByEmailAsc(pageable);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
