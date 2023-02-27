package com.sarahjting.roost.user;

import com.sarahjting.roost.common.security.UserDetailsAdapter;
import com.sarahjting.roost.user.projections.UserBasicProjection;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me")
public class MeController {
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public UserBasicProjection getMe(
        @AuthenticationPrincipal UserDetailsAdapter userDetails
    ) {
        return UserBasicProjection.fromUser(userDetails.getUser());
    }
}
