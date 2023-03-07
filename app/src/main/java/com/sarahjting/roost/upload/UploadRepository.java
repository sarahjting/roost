package com.sarahjting.roost.upload;

import com.sarahjting.roost.upload.projections.UploadBasicProjection;
import com.sarahjting.roost.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UploadRepository extends JpaRepository<Upload, UUID> {
    Optional<Upload> findByUserAndFileName(User user, String fileName);

    // this is a combination of JPQL + SPEL! neat
    // the pageable is automatically supported as long as JPQL is used and not native SQL
    @Query(
        "SELECT new com.sarahjting.roost.upload.projections.UploadBasicProjection(u.id, u.createdAt, u.updatedAt, u.storage, u.type, u.fileName, u.fileSize, u.mimeType, u.imageWidth, u.imageHeight, u.originalFileName) " +
            "FROM Upload u WHERE u.user = :#{#user} AND u.status = :#{T(com.sarahjting.roost.upload.UploadStatus).UPLOADED}"
    )
    Page<UploadBasicProjection> findBasicPageByUser(
        @Param("user") User user,
        Pageable pageable
    );
}
