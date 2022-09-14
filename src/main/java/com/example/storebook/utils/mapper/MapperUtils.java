package com.example.storebook.utils.mapper;

import org.hibernate.Hibernate;
import org.mapstruct.Condition;

import java.util.Collection;

/**
 * @author pashtet
 */
public class MapperUtils {
    private MapperUtils() {
        throw new IllegalStateException("Utility class");
    }

    @Condition
    public static <T> boolean isInitialized(Collection<T> collection) {
        return Hibernate.isInitialized(collection) && collection != null;
    }
}
