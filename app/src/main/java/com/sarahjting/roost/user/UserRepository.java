package com.sarahjting.roost.user;

import com.sarahjting.roost.user.projections.UserBasicProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Slice<UserBasicProjection> findBasicSliceByOrderByEmailAsc(Pageable pageable);
    Optional<User> findOneByUsername(String email);
    Optional<User> findOneByEmail(String email);
}
