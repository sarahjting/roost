package com.sarahjting.roost.upload;

import com.sarahjting.roost.upload.projections.UploadBasicProjection;
import com.sarahjting.roost.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UploadRepository extends JpaRepository<Upload, UUID> {
    Optional<Upload> findByUserAndFileName(User user, String fileName);
    Page<UploadBasicProjection> findBasicPageByUser(User user, Pageable pageable);
}
