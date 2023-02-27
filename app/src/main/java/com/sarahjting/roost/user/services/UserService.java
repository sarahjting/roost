package com.sarahjting.roost.user.services;

import com.sarahjting.roost.user.User;
import com.sarahjting.roost.user.projections.UserBasicProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<User> findOneById(UUID id);
    Optional<User> findOneByEmail(String email);
    Slice<UserBasicProjection> findBasicSlice(Pageable pageable);
    void delete(User user);
    Long countAll();
}
