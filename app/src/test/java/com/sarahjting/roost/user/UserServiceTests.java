package com.sarahjting.roost.user;

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
    EntityManager entityManager;

    @Test
    public void givenSave_whenNewUser_thenCreateUser()
    {
        User newUser = new User("test@example.com", "p4$$w0Rd");
        User savedUser = userService.save(newUser);
        User retrievedUser = entityManager.find(User.class, savedUser.getId());

        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser).isEqualTo(savedUser);
    }

    @Test
    public void givenSave_whenExistingUser_thenUpdateUser()
    {
        User newUser = new User("test@example.com", "p4$$w0Rd");
        newUser = userService.save(newUser);
        newUser.setEmail("test-updated@example.com");
        User savedUser = userService.save(newUser);

        User retrievedUser = entityManager.find(User.class, savedUser.getId());
        assertThat(retrievedUser.getEmail()).isEqualTo(newUser.getEmail());
        assertThat(savedUser.getEmail()).isEqualTo(savedUser.getEmail());
    }

    @Test
    public void givenUsers_thenSlice()
    {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i ++) {
            User newUser = new User("test" + i + "@example.com", "p4$$w0Rd");
            newUser = userService.save(newUser);
            users.add(newUser);
        }

        Slice<User> firstSlice = userService.findSlice(PageRequest.of(0, 2));
        assertThat(firstSlice.getNumberOfElements()).isEqualTo(2);
        assertThat(firstSlice.getContent()).containsAll(Set.of(users.get(0), users.get(1)));
        assertThat(firstSlice.hasNext()).isTrue();
        assertThat(firstSlice.hasPrevious()).isFalse();

        Slice<User> middleSlice = userService.findSlice(PageRequest.of(1, 2));
        assertThat(middleSlice.getNumberOfElements()).isEqualTo(2);
        assertThat(middleSlice.getContent()).containsAll(Set.of(users.get(2), users.get(3)));
        assertThat(middleSlice.hasNext()).isTrue();
        assertThat(middleSlice.hasPrevious()).isTrue();

        Slice<User> lastSlice = userService.findSlice(PageRequest.of(2, 2));
        assertThat(lastSlice.getNumberOfElements()).isEqualTo(1);
        assertThat(lastSlice.getContent()).containsAll(Set.of(users.get(4)));
        assertThat(lastSlice.hasNext()).isFalse();
        assertThat(lastSlice.hasPrevious()).isTrue();
    }
}
