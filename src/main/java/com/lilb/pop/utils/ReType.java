package com.lilb.pop.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 查询返回json格式依照ui默认属性名称
 */
@Data
public class ReType implements Serializable{
  /**状态*/
  public int code=0;
  /**状态信息*/
  public String msg="";
  /**数据总数*/
  public long total;
  /**页码*/
  public long pageNum;

  public List<?> data;

  public ReType() {
  }

  public ReType(long total, List<?> data) {
    this.total = total;
    this.data = data;
  }

  public ReType(long total,long pageNum, List<?> data) {
    this.total = total;
    this.pageNum=pageNum;
    this.data = data;
  }
}
