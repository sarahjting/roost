package com.sarahjting.roost.storage.validation;

import com.sarahjting.roost.storage.StorageDriver;
import com.sarahjting.roost.storage.StorageDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StorageDtoValidator implements ConstraintValidator<StorageDtoAnnotation, StorageDto> {
    @Override
    public boolean isValid(StorageDto storageDto, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();

        if (storageDto.getDriver() == StorageDriver.S3) {
            if (storageDto.getRegion() == null || storageDto.getRegion().isBlank()) {
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate("Region must be provided.")
                        .addPropertyNode("region")
                        .addConstraintViolation();
                return false;
            }
        }

        if (storageDto.getDriver() != StorageDriver.S3) {
            if (storageDto.getEndpoint() == null || storageDto.getEndpoint().isBlank()) {
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate("Endpoint must be provided.")
                        .addPropertyNode("endpoint")
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }

    @Override
    public void initialize(StorageDtoAnnotation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
