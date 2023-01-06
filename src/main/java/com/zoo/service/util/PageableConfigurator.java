package com.zoo.service.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class PageableConfigurator {

    private PageableConfigurator() {
        throw new IllegalStateException("Utility class");
    }

    public static Pageable preparePageable(Integer size, Integer page, String sortProperty, String sortDirection) {
        if (Objects.isNull(sortDirection) || Objects.isNull(sortProperty)) {
            return PageRequest.of(Objects.isNull(page) ? 0 : page, Objects.isNull(size) ? Integer.MAX_VALUE : size);
        }

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortProperty);
        return PageRequest.of(Objects.isNull(page) ? 0 : page, Objects.isNull(size) ? Integer.MAX_VALUE : size, sort);
    }
}
