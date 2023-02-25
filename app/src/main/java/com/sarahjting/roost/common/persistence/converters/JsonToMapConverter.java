package com.sarahjting.roost.common.persistence.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// thank u heril muratovic
// https://stackoverflow.com/questions/44308167/how-to-map-a-mysql-json-column-to-a-java-entity-property-using-jpa-and-hibernate
@Converter
public class JsonToMapConverter implements AttributeConverter<Map<String, Object>, String>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonToMapConverter.class);

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> convertToEntityAttribute(String attribute)
    {
        if (attribute == null) {
            return new HashMap<>();
        }

        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(attribute, HashMap.class);
        }
        catch (IOException e) {
            LOGGER.error("Convert error while trying to convert string(JSON) to map data structure.");
        }

        return new HashMap<>();
    }

    @Override
    public String convertToDatabaseColumn(Map<String, Object> dbData)
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(dbData);
        }
        catch (JsonProcessingException e)
        {
            LOGGER.error("Could not convert map to json string.");
            return null;
        }
    }
}