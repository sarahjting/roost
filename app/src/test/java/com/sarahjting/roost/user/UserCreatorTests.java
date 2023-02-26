package com.sarahjting.roost.user;

import com.sarahjting.roost.user.services.UserCreator;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Rollback
@Transactional
public class UserCreatorTests {
    @Autowired
    UserCreator userCreator;

    @Autowired
    EntityManager entityManager;

    @Test
    public void givenValidDto_thenCreateUser()
    {
        UserDto userDto = new UserDto("test@example.com", "p4$$w0Rd");
        User savedUser = userCreator.execute(userDto);
        User retrievedUser = entityManager.find(User.class, savedUser.getId());

        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser).isEqualTo(savedUser);
    }
}
