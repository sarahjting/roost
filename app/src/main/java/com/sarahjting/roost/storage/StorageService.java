package com.sarahjting.roost.storage;

import com.sarahjting.roost.storage.projections.StorageBasicProjection;
import com.sarahjting.roost.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;
import java.util.UUID;

public interface StorageService {
    Storage save(Storage storage);
    Optional<Storage> findAuthorizedById(User user, UUID id);
    Slice<StorageBasicProjection> findAuthorizedBasicSlice(User user, Pageable pageable);
    void delete(Storage storage);
}
