package com.sarahjting.roost.user;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Slice<User> findSliceByOrderByEmailAsc(Pageable pageable);

    Optional<User> findOneByEmail(String email);
}
