package com.sarahjting.roost.upload;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UploadLogRepository extends JpaRepository<UploadLog, UUID> {
}
