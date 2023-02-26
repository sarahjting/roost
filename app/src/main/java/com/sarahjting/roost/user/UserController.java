package com.sarahjting.roost.user;

import com.sarahjting.roost.user.projections.UserBasicProjection;
import com.sarahjting.roost.user.services.UserCreator;
import com.sarahjting.roost.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserCreator userCreator;

    @GetMapping
    public Slice<UserBasicProjection> index(
        @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        return userService.findSlice(PageRequest.of(page - 1, 15));
    }

    @GetMapping("{id}")
    public Optional<User> getUserById(
        @PathVariable("id") UUID id
    ) {
        return userService.findOneById(id);
    }

    @PostMapping
    public User createUser(
        @RequestBody UserDto userDto
    ) {
        return userCreator.execute(userDto);
    }
}
