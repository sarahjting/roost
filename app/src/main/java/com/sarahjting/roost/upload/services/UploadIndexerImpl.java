package com.sarahjting.roost.upload.services;

import com.sarahjting.roost.upload.UploadRepository;
import com.sarahjting.roost.upload.projections.UploadBasicProjection;
import com.sarahjting.roost.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UploadIndexerImpl implements UploadIndexer {
    @Autowired
    UploadRepository uploadRepository;

    @Override
    public Page<UploadBasicProjection> findAuthorizedBasicPage(User user, Pageable pageable) {
        return uploadRepository.findBasicPageByUser(user, pageable);
    }
}
