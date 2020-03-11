package com.cnki.entity;

import lombok.Data;

/**
 * @author li-lb
 */
@Data
public class BeanSearch {
    private Pagination pagination;
    private String keyword;
}
