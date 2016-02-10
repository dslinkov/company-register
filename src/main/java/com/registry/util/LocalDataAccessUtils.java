package com.registry.util;

import java.util.Collection;
import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.support.DataAccessUtils;

public class LocalDataAccessUtils extends DataAccessUtils {

    public static <T> Optional<T> optional(Collection<T> results) {

        int size = (results != null ? results.size() : 0);
        if (size == 0) {
            return Optional.empty();
        }
        if (size > 1) {
            throw new IncorrectResultSizeDataAccessException(1, size);
        }
        return Optional.ofNullable(results.iterator().next());
    }

}
