package com.api.bookstore.utils;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtils {

    public static Pageable transformePagination(Integer limit, Integer size, String sort){
        String[] sortBy = sort.split(",");

        Pageable pageable;

        if (sortBy.length == 2) {
            pageable = PageRequest.of(limit, size, Sort.Direction.fromString(sortBy[1]), sortBy[0]);
        } else {
            pageable = PageRequest.of(limit, size, Sort.Direction.ASC, "id");
        }

        return pageable;
    }


}
