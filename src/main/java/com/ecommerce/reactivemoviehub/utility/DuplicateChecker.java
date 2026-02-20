package com.ecommerce.reactivemoviehub.utility;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DuplicateChecker {
    public static <T> boolean hasDuplicates(Collection<T> items) {
        Set<T> set = new HashSet<>();
        return items.stream().anyMatch(item -> !set.add(item));
    }

    public static <T> void throwIfDuplicates(Collection<T> items, String message) {
        if (hasDuplicates(items)) {
            throw new RuntimeException(message);
        }
    }
}
