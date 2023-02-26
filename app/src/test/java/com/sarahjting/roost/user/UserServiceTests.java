package com.sarahjting.roost.user;

import com.sarahjting.roost.user.projections.UserBasicProjection;
import com.sarahjting.roost.user.services.UserCreator;
import com.sarahjting.roost.user.services.UserService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Rollback
@Transactional
public class UserServiceTests {
    @Autowired
    UserService userService;

    @Autowired
    UserCreator userCreator;

    @Autowired
    EntityManager entityManager;

    @Test
    public void givenUsers_thenSlice()
    {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i ++) {
            User newUser = userCreator.execute(new UserDto("test" + i + "@example.com", "p4$$w0Rd"));
            users.add(newUser);
        }

        Slice<UserBasicProjection> firstSlice = userService.findSlice(PageRequest.of(0, 2));
        assertThat(firstSlice.getNumberOfElements()).isEqualTo(2);
        assertThat(firstSlice.getContent().stream().map(t -> t.getId())).containsAll(Set.of(users.get(0).getId(), users.get(1).getId()));
        assertThat(firstSlice.hasNext()).isTrue();
        assertThat(firstSlice.hasPrevious()).isFalse();

        Slice<UserBasicProjection> middleSlice = userService.findSlice(PageRequest.of(1, 2));
        assertThat(middleSlice.getNumberOfElements()).isEqualTo(2);
        assertThat(middleSlice.getContent().stream().map(t -> t.getId())).containsAll(Set.of(users.get(2).getId(), users.get(3).getId()));
        assertThat(middleSlice.hasNext()).isTrue();
        assertThat(middleSlice.hasPrevious()).isTrue();

        Slice<UserBasicProjection> lastSlice = userService.findSlice(PageRequest.of(2, 2));
        assertThat(lastSlice.getNumberOfElements()).isEqualTo(1);
        assertThat(lastSlice.getContent().stream().map(t -> t.getId())).containsAll(Set.of(users.get(4).getId()));
        assertThat(lastSlice.hasNext()).isFalse();
        assertThat(lastSlice.hasPrevious()).isTrue();
    }
}
