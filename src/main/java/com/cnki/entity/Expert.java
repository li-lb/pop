package com.cnki.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author li-lb
 */
@Data
public class Expert implements Serializable {
    private Integer id;

    private String name;

    private String type;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}