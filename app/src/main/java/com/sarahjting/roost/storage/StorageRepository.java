package com.sarahjting.roost.storage;

import com.sarahjting.roost.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StorageRepository extends JpaRepository<Storage, UUID> {
    Slice<Storage> findSliceByUser(User user, Pageable pageable);
}
