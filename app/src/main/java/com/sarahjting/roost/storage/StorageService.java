package com.sarahjting.roost.storage;

import com.sarahjting.roost.storage.projections.StorageBasicProjection;
import com.sarahjting.roost.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;
import java.util.UUID;

public interface StorageService {
    Storage save(Storage storage);
    Optional<StorageBasicProjection> findBasicById(UUID id);
    Slice<StorageBasicProjection> findBasicSliceByUser(User user, Pageable pageable);
    Slice<Storage> findSliceByUser(User user, Pageable pageable);
    void delete(Storage storage);
}
