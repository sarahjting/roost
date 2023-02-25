package com.sarahjting.roost.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findOneById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Slice<User> findSlice(Pageable pageable) {
        return userRepository.findSliceBy(pageable);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}
