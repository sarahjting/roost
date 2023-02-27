package com.sarahjting.roost.user;

import com.sarahjting.roost.user.projections.UserBasicProjection;
import com.sarahjting.roost.user.services.UserCreator;
import com.sarahjting.roost.user.services.UserService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
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
    @PermitAll
    public User createUser(
        @RequestBody UserDto userDto
    ) {
        return userCreator.execute(userDto);
    }
}
