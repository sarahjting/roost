package com.sarahjting.roost.storage;

import com.sarahjting.roost.storage.projections.StorageBasicProjection;
import com.sarahjting.roost.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    StorageRepository storageRepository;

    @Override
    @Transactional
    public Storage save(Storage storage) {
        return storageRepository.save(storage);
    }

    @Override
    public Optional<StorageBasicProjection> findBasicById(UUID id) {
        return storageRepository.findById(id, StorageBasicProjection.class);
    }

    @Override
    public Slice<StorageBasicProjection> findBasicSliceByUser(User user, Pageable pageable) {
        return storageRepository.findSliceByUser(user, pageable, StorageBasicProjection.class);
    }

    @Override
    public void delete(Storage storage) {
        storageRepository.delete(storage);
    }
}
