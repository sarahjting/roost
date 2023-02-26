package com.sarahjting.roost.storage;

import com.sarahjting.roost.common.security.UserDetailsAdapter;
import com.sarahjting.roost.storage.projections.StorageBasicProjection;
import com.sarahjting.roost.storage.services.StorageCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.data.web.ProjectedPayload;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/me/storages")
public class StorageController {
    @Autowired
    StorageService storageService;

    @Autowired
    StorageCreator storageCreator;

    @GetMapping
    public Slice<StorageBasicProjection> index(
        @AuthenticationPrincipal UserDetailsAdapter userDetails,
        @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        return storageService.findBasicSliceByUser(userDetails.getUser(), PageRequest.of(page - 1, 15));
    }

    @PostMapping
    public StorageBasicProjection create(
        @AuthenticationPrincipal UserDetailsAdapter userDetails,
        @RequestBody StorageDto storageDto
    ) {
        Storage newStorage = storageCreator.execute(userDetails.getUser(), storageDto);
        return new StorageBasicProjection(newStorage);
    }
}
