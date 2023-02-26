package com.sarahjting.roost.storage;

import com.sarahjting.roost.storage.projections.StorageBasicProjection;
import com.sarahjting.roost.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StorageRepository extends JpaRepository<Storage, UUID> {
    Slice<StorageBasicProjection> findBasicSliceByUser(User user, Pageable pageable);
    Optional<StorageBasicProjection> findBasicById(UUID id);
}
