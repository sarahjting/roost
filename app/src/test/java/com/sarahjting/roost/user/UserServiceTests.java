package com.sarahjting.roost.user;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
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
}
