package coreserech.cvurl.io.mapper.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coreserech.cvurl.io.exception.MappingException;
import coreserech.cvurl.io.mapper.GenericMapper;

import java.io.IOException;

public class JacksonMapper implements GenericMapper {

    private ObjectMapper objectMapper;

    public JacksonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T readValue(String value, Class<T> valueType) {
        try {
            return this.objectMapper.readValue(value, valueType);
        } catch (IOException e) {
            throw new MappingException(e.getMessage(), e);
        }
    }

    @Override
    public String writeValue(Object value) {
        try {
            return this.objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new MappingException(e.getMessage(), e);
        }
    }
}
