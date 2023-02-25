package com.sarahjting.roost.upload;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UploadRepository extends JpaRepository<Upload, UUID> {
}
