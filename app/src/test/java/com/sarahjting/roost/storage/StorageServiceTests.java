package com.sarahjting.roost.storage;

import com.sarahjting.roost.user.User;
import com.sarahjting.roost.user.UserDto;
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
public class StorageServiceTests {
    @Autowired
    private StorageService storageService;

    @Autowired
    private UserCreator userCreator;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void givenSave_whenNewStorage_thenCreateStorage()
    {
        User user = userCreator.execute(new UserDto("test@example.com", "p4$$w0RD!"));

        Storage newStorage = new Storage(user, "New Storage", StorageDriver.B2);
        Storage savedStorage = storageService.save(newStorage);
        Storage retrievedStorage = entityManager.find(Storage.class, savedStorage.getId());

        assertThat(retrievedStorage).isNotNull();
        assertThat(retrievedStorage).isEqualTo(savedStorage);
    }
}
