package com.cnki.entity;

import lombok.Data;

/**
 * @author li-lb
 */
@Data
public class Pagination {
    private int pageNum = 1;
    private int pageSize = 20;
    private int total;
}
