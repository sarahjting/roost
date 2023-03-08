package com.sarahjting.roost.user.services;

import com.sarahjting.roost.user.User;
import com.sarahjting.roost.user.projections.UserBasicProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface UserService {
    Optional<User> findOneByUsername(String username);

    Optional<User> findOneByEmail(String email);
    Slice<UserBasicProjection> findBasicSlice(Pageable pageable);
    void delete(User user);
    User save(User user);
}
