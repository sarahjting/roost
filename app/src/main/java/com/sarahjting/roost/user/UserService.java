package com.sarahjting.roost.user;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User save(User user);
    Optional<User> findOneById(UUID id);
    Optional<User> findOneByEmail(String email);
    Slice<User> findSlice(Pageable pageable);
    void delete(User user);
}
