package com.sarahjting.roost.upload;

import com.sarahjting.roost.common.exceptions.NoDefaultStorageException;
import com.sarahjting.roost.common.security.UserDetailsAdapter;
import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.upload.projections.UploadBasicProjection;
import com.sarahjting.roost.upload.services.UploadCreator;
import com.sarahjting.roost.upload.services.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZoneOffset;
import java.util.*;

@RestController
@RequestMapping("/api/me/uploads")
@PreAuthorize("hasRole('USER')")
public class MeUploadController {
    private static Logger logger = LoggerFactory.getLogger(MeUploadController.class);

    @Autowired
    UploadCreator uploadCreator;

    @Autowired
    UploadService uploadIndexer;

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
            Math.max(0, Integer.valueOf(page)),
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

                // create a projection from an entity
                ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
                res.add(projectionFactory.createProjection(UploadBasicProjection.class, upload));
            } catch (IOException e) {
                logger.error("Error occurred while uploading file " + i + " of payload", e);
                res.add(null);
            }
        }

        return res;
    }

    // this is intended to mirror imgur's upload API so we can plug it into different clients
    // https://apidocs.imgur.com/#c85c9dfc-7487-4de2-9ecd-66f727cf3139
    @PostMapping(path = "imgur", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, ?> imgurCreate(
        @AuthenticationPrincipal UserDetailsAdapter userDetails,
        @RequestParam(value = "title", defaultValue = "", required = false) String title,
        @RequestParam(value = "description", defaultValue = "", required = false) String description,
        @RequestParam("image") MultipartFile file
    ) throws NoDefaultStorageException, IOException {
        Storage storage = userDetails.getUser().getDefaultStorage();
        if (storage == null) {
            throw new NoDefaultStorageException();
        }

        Upload upload = uploadCreator.execute(userDetails.getUser(), storage, file);

        Map<String, Object> resData = new HashMap<>();
        resData.put("id", upload.getId());
        resData.put("title", null);
        resData.put("description", null);
        resData.put("datatime", upload.getCreatedAt().toEpochSecond(ZoneOffset.UTC));
        resData.put("type", upload.getMimeType().toString());
        resData.put("animated", false); // TODO: implement animation checking
        resData.put("height", upload.getImageHeight());
        resData.put("width", upload.getImageWidth());
        resData.put("size", upload.getFileSize());
        resData.put("views", 0);
        resData.put("bandwidth", 0);
        resData.put("vote", null);
        resData.put("favorite", 0);
        resData.put("nsfw", null);
        resData.put("section", null);
        resData.put("account_url", null);
        resData.put("account_id", 0);
        resData.put("is_ad", false);
        resData.put("in_most_viral", false);
        resData.put("tags", new ArrayList<>());
        resData.put("ad_type", 0);
        resData.put("ad_url", "");
        resData.put("in_gallery", false);
        resData.put("deletehash", "");
        resData.put("name", "");
        resData.put("link", upload.getFileUrl());

        Map<String, Object> res = new HashMap<>();
        res.put("success", true);
        res.put("status", HttpStatus.ACCEPTED);
        res.put("data", resData);

        return res;
    }
}
