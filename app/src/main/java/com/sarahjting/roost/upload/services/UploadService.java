package com.sarahjting.roost.upload.services;

import com.sarahjting.roost.upload.projections.UploadBasicProjection;
import com.sarahjting.roost.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UploadService {
    <T> Optional<T> findOneByUserAndFileName(User user, String fileName);
    Page<UploadBasicProjection> findAuthorizedBasicPage(User user, Pageable pageable);
}
