package com.sarahjting.roost.upload;

import com.sarahjting.roost.common.exceptions.NoDefaultStorageException;
import com.sarahjting.roost.common.security.UserDetailsAdapter;
import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.upload.projections.UploadBasicProjection;
import com.sarahjting.roost.upload.services.UploadCreator;
import com.sarahjting.roost.upload.services.UploadIndexer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/me/uploads")
@PreAuthorize("hasRole('USER')")
public class MeUploadController {
    private static Logger logger = LoggerFactory.getLogger(MeUploadController.class);

    @Autowired
    UploadCreator uploadCreator;

    @Autowired
    UploadIndexer uploadIndexer;

    @GetMapping
    public Page<UploadBasicProjection> index(
        @AuthenticationPrincipal UserDetailsAdapter userDetailsAdapter,
        @RequestParam(name = "page", defaultValue = "1") String page,
        @RequestParam(name = "sort", defaultValue = "createdAt_desc") String sort
    ) {
        Set<String> sortableFields = Set.of("createdAt", "updatedAt", "fileSize");
        String[] sortParts = sort.split("_");
        String sortByField = sortParts[0];
        String sortByDirection = sortParts.length > 1 ? sortParts[1] : "asc";

        if (!sortableFields.contains(sortByField)) {
            sortByField = "createdAt";
        }

        Pageable pageable = PageRequest.of(
            Math.max(0, Integer.valueOf(page) - 1),
            24,
            Sort.by(
                sortByDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
                sortByField
            )
        );

        return uploadIndexer.findAuthorizedBasicPage(userDetailsAdapter.getUser(), pageable);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public List<UploadBasicProjection> create(
        @AuthenticationPrincipal UserDetailsAdapter userDetails,
        @RequestParam("files") ArrayList<MultipartFile> files
    ) throws NoDefaultStorageException {
        List<UploadBasicProjection> res = new ArrayList<>();

        if (files.size() == 0) {
            return null;
        }

        // TODO: allow storage to be provided by the user
        Storage storage = userDetails.getUser().getDefaultStorage();
        if (storage == null) {
            throw new NoDefaultStorageException();
        }

        for (int i = 0; i < files.size(); i ++) {
            try {
                Upload upload = uploadCreator.execute(userDetails.getUser(), storage, files.get(i));
                res.add(UploadBasicProjection.fromUpload(upload));
            } catch (IOException e) {
                logger.error("Error occurred while uploading file " + i + " of payload", e);
                res.add(null);
            }
        }

        return res;
    }
}
