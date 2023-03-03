package com.sarahjting.roost.storage.services;

import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.storage.StorageDriver;
import com.sarahjting.roost.storage.StorageDto;
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
public class StorageCreatorTests {
    @Autowired
    private StorageCreator storageCreator;

    @Autowired
    private UserCreator userCreator;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void givenValidInputs_thenCreateStorage()
    {
        User user = userCreator.execute(new UserDto("test@example.com", "p4$$w0RD!"));

        StorageDto storageDto = new StorageDto();
        storageDto.setName("Storage name");
        storageDto.setDriver(StorageDriver.B2);
        storageDto.setEndpoint("endpoint");
        storageDto.setAccessKey("access key");
        storageDto.setSecretKey("secret key");
        storageDto.setBucketName("bucket name");

        Storage savedStorage = storageCreator.execute(user, storageDto);
        Storage retrievedStorage = entityManager.find(Storage.class, savedStorage.getId());

        assertThat(retrievedStorage).isNotNull();
        assertThat(retrievedStorage).isEqualTo(savedStorage);
    }
}
