package com.sarahjting.roost.user;

import com.sarahjting.roost.common.security.UserDetailsAdapter;
import com.sarahjting.roost.user.projections.UserBasicProjection;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/me")
public class MeController {
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public UserBasicProjection getMe(
        @AuthenticationPrincipal UserDetailsAdapter userDetails
    ) {
        ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
        return projectionFactory.createProjection(UserBasicProjection.class, userDetails.getUser());
    }
}
