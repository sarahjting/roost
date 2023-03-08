package com.sarahjting.roost.upload.services;

import com.sarahjting.roost.upload.UploadRepository;
import com.sarahjting.roost.upload.UploadStatus;
import com.sarahjting.roost.upload.projections.UploadBasicProjection;
import com.sarahjting.roost.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    UploadRepository uploadRepository;

    @Override
    public <T> Optional<T> findOneByUserAndFileName(User user, String fileName) {
        return uploadRepository.findOneByUserAndFileName(user, fileName);
    }

    @Override
    public Page<UploadBasicProjection> findAuthorizedBasicPage(User user, Pageable pageable) {
        return uploadRepository.findPageByUserAndStatus(user, UploadStatus.UPLOADED, pageable);
    }
}
