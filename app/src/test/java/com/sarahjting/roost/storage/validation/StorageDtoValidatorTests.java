package com.sarahjting.roost.storage.validation;

import com.sarahjting.roost.storage.StorageDriver;
import com.sarahjting.roost.storage.StorageDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StorageDtoValidatorTests {
    static private Logger logger = LoggerFactory.getLogger(StorageDtoValidator.class);

    @Test
    public void givenStorage_whenValid_thenPassValidation() {
        assertStorageDtoIsValid(buildValidDto());
    }

    @Test
    public void givenStorageDto_whenInvalidName_thenFailValidation() {
        StorageDto storageDto = buildValidDto();
        storageDto.setName("");
        assertStorageDtoIsInvalid(storageDto, "name");
    }

    @Test
    public void givenStorageDto_whenInvalidEndpoint_thenFailValidation() {
        StorageDto storageDto = buildValidDto();
        storageDto.setEndpoint("");
        assertStorageDtoIsInvalid(storageDto, "endpoint");
    }

    @Test
    public void givenStorageDto_whenInvalidRegion_thenFailValidation() {
        StorageDto storageDto = buildValidDto();
        storageDto.setDriver(StorageDriver.S3);
        assertStorageDtoIsInvalid(storageDto, "region");

        storageDto = buildValidDto();
        storageDto.setDriver(StorageDriver.S3);
        storageDto.setEndpoint("");
        storageDto.setRegion("region");
        assertStorageDtoIsValid(storageDto);
    }

    @Test
    public void givenStorageDto_whenInvalidAccessKey_thenFailValidation() {
        StorageDto storageDto = buildValidDto();
        storageDto.setAccessKey("");
        assertStorageDtoIsInvalid(storageDto, "accessKey");
    }

    @Test
    public void givenStorageDto_whenInvalidSecretKey_thenFailValidation() {
        StorageDto storageDto = buildValidDto();
        storageDto.setSecretKey("");
        assertStorageDtoIsInvalid(storageDto);
    }

    @Test
    public void givenStorageDto_whenInvalidBucket_thenFailValidation() {
        StorageDto storageDto = buildValidDto();
        storageDto.setBucketName("");
        assertStorageDtoIsInvalid(storageDto, "bucketName");
    }

    private StorageDto buildValidDto() {
        StorageDto storageDto = new StorageDto();
        storageDto.setName("Storage name");
        storageDto.setDriver(StorageDriver.B2);
        storageDto.setEndpoint("endpoint");
        storageDto.setAccessKey("access key");
        storageDto.setSecretKey("secret key");
        storageDto.setBucketName("bucket name");
        return storageDto;
    }

    private void assertStorageDtoIsValid(StorageDto storageDto) {
        Set<ConstraintViolation<StorageDto>> constraintViolations = validate(storageDto);
        assertThat(constraintViolations).isEmpty();
    }

    private Set<ConstraintViolation<StorageDto>> assertStorageDtoIsInvalid(StorageDto storageDto) {
        Set<ConstraintViolation<StorageDto>> constraintViolations = validate(storageDto);
        assertThat(constraintViolations).isNotEmpty();
        return constraintViolations;
    }

    private Set<ConstraintViolation<StorageDto>> assertStorageDtoIsInvalid(StorageDto storageDto, String fieldName) {
        Set<ConstraintViolation<StorageDto>> constraintViolations = assertStorageDtoIsInvalid(storageDto);
        assertThat(constraintViolations.stream().filter(v -> v.getPropertyPath().toString().equals(fieldName))).isNotEmpty();
        return constraintViolations;
    }

    private Set<ConstraintViolation<StorageDto>> validate(StorageDto storageDto) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<StorageDto>> violations = validator.validate(storageDto);
        return violations;
    }
}
