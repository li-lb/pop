package com.lilb.pop.mapper;

import com.lilb.pop.entity.Expert;
import java.util.List;

public interface ExpertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Expert record);

    Expert selectByPrimaryKey(Integer id);

    List<Expert> selectAll();

    int updateByPrimaryKey(Expert record);

    List<Expert> selectListByPage(Expert record);
}