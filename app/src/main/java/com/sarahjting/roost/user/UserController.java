package com.sarahjting.roost.user;

import com.sarahjting.roost.storage.projections.StorageBasicProjection;
import com.sarahjting.roost.user.projections.UserBasicProjection;
import com.sarahjting.roost.user.services.UserCreator;
import com.sarahjting.roost.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserCreator userCreator;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Slice<UserBasicProjection> index(
        @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        return userService.findBasicSlice(PageRequest.of(page - 1, 15));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserBasicProjection createUser(
        @RequestBody @Validated UserDto userDto
    ) {
        // TODO: we should catch the unique key conflict exception here and re-throw it as a validation exception...
        ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
        return projectionFactory.createProjection(UserBasicProjection.class, userCreator.execute(userDto));
    }
}
