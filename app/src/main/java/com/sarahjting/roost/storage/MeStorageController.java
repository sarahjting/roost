package com.sarahjting.roost.storage;

import com.sarahjting.roost.common.security.UserDetailsAdapter;
import com.sarahjting.roost.storage.projections.StorageBasicProjection;
import com.sarahjting.roost.storage.services.StorageCreator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
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

    @GetMapping
    public Slice<StorageBasicProjection> index(
        @AuthenticationPrincipal UserDetailsAdapter userDetails,
        @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        return storageService.findAuthorizedBasicSlice(userDetails.getUser(), PageRequest.of(page - 1, 15));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StorageBasicProjection create(
            @AuthenticationPrincipal UserDetailsAdapter userDetails,
            @RequestBody @Validated StorageDto storageDto
    ) {
        Storage newStorage = storageCreator.execute(userDetails.getUser(), storageDto);
        return StorageBasicProjection.fromStorage(newStorage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(
            @AuthenticationPrincipal UserDetailsAdapter userDetails,
            @PathVariable("id") UUID id
    ) {
        // it might be a better idea to pull the storage down first
        // then check the user ID in the application layer (this allows us to show a more detailed error message)
        // but i did want to try using compound predicates
        Optional<Storage> storage = storageService.findAuthorizedById(userDetails.getUser(), id);
        if (storage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No storage found.");
        }
        storageService.delete(storage.get());
    }
}
