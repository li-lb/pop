package com.lilb.pop.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lilb.pop.entity.BeanSearch;
import com.lilb.pop.entity.Expert;
import com.lilb.pop.mapper.ExpertMapper;
import com.lilb.pop.utils.ReType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author li-lb
 */
@Controller
@RequestMapping(value = "index")
public class IndexController {

    @Autowired
    ExpertMapper expertMapper;
    /**
     * 专家列表
     * @param beanSearch
     * @return
     */
    @PostMapping(value = "pageList")
    @ResponseBody
    public ReType pageList(@RequestBody BeanSearch beanSearch) {
        ReType reType = new ReType();
        List<Expert> tList = null;
        Page<Expert> tPage = PageHelper.startPage(beanSearch.getPagination().getPageNum(), beanSearch.getPagination().getPageSize());
        Expert expert = new Expert();
        expert.setType(beanSearch.getKeyword());
        tList = expertMapper.selectListByPage(expert);
        reType.setData(tList);
        reType.setTotal(tPage.getTotal());
        return reType;
    }
}
