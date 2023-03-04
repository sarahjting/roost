package com.sarahjting.roost.upload.services;

import com.sarahjting.roost.upload.projections.UploadBasicProjection;
import com.sarahjting.roost.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UploadIndexer {
    Page<UploadBasicProjection> findAuthorizedBasicPage(User user, Pageable pageable);
}
