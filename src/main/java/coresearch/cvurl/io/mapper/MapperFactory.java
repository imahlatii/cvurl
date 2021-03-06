package coresearch.cvurl.io.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import coresearch.cvurl.io.mapper.impl.JacksonMapper;

import static java.lang.String.format;

/**
 * Factory with handy methods to create instances of {@link GenericMapper}.
 */
public class MapperFactory {

    /**
     * Creates default implementation of {@link GenericMapper}.
     *
     * @return default implementation of {@link GenericMapper}
     */
    public static GenericMapper createDefault() {
        return from(new ObjectMapper());
    }

    /**
     * Creates {@link JacksonMapper} from specified {@link ObjectMapper}.
     *
     * @param objectMapper mapper from which JacksonMapper will be created.
     * @return new {@link JacksonMapper}
     */
    public static GenericMapper from(ObjectMapper objectMapper) {
        return new JacksonMapper(objectMapper);
    }

    private MapperFactory() {
        throw new IllegalStateException(format("Creating of class %s is forbidden", MapperFactory.class.getName()));
    }
}
