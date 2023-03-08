package com.sarahjting.roost.storage;

import com.sarahjting.roost.common.security.UserDetailsAdapter;
import com.sarahjting.roost.storage.projections.StorageBasicProjection;
import com.sarahjting.roost.storage.services.StorageCreator;
import com.sarahjting.roost.upload.projections.UploadBasicProjection;
import com.sarahjting.roost.user.User;
import com.sarahjting.roost.user.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/me/storages")
@PreAuthorize("hasRole('USER')")
public class MeStorageController {
    @Autowired
    StorageService storageService;

    @Autowired
    StorageCreator storageCreator;

    @Autowired
    UserService userService;

    @GetMapping
    public Slice<StorageBasicProjection> index(
        @AuthenticationPrincipal UserDetailsAdapter userDetails,
        @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        return storageService.findAuthorizedBasicSlice(userDetails.getUser(), PageRequest.of(page - 1, 15));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public StorageBasicProjection create(
        @AuthenticationPrincipal UserDetailsAdapter userDetails,
        @RequestBody @Validated StorageDto storageDto
    ) {
        Storage newStorage = storageCreator.execute(userDetails.getUser(), storageDto);
        ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
        return projectionFactory.createProjection(StorageBasicProjection.class, newStorage);
    }

    @PostMapping("/{id}/set-default")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public StorageBasicProjection setDefault(
        @AuthenticationPrincipal UserDetailsAdapter userDetails,
        @PathVariable("id") UUID id
    ) {
        User user = userDetails.getUser();
        Optional<Storage> storage = storageService.findAuthorizedById(user, id);

        if (storage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No storage found.");
        }

        user.setDefaultStorage(storage.get());
        userService.save(user);

        ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
        return projectionFactory.createProjection(StorageBasicProjection.class, storage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public void delete(
        @AuthenticationPrincipal UserDetailsAdapter userDetails,
        @PathVariable("id") UUID id
    ) {
        // it might be a better idea to pull the storage down first
        // then check the user ID in the application layer (this allows us to show a more detailed error message)
        // but i did want to try using compound predicates

        User user = userDetails.getUser();

        Optional<Storage> storageResult = storageService.findAuthorizedById(user, id);
        if (storageResult.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No storage found.");
        }

        Storage storage = storageResult.get();

        if (storage.equals(user.getDefaultStorage())) {
            user.setDefaultStorage(null);
            userService.save(user);
        }

        storageService.delete(storage);
    }
}
