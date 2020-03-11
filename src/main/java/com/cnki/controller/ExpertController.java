package com.cnki.controller;

import com.cnki.entity.BeanSearch;
import com.cnki.entity.Expert;
import com.cnki.service.ExpertService;
import com.cnki.utils.ReType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @author li-lb
 */
@Controller
@RequestMapping(value = "expert")
public class ExpertController {

    @Autowired
    ExpertService expertService;
    /**
     * 专家列表
     * @param beanSearch
     * @return
     */
    @RequestMapping(value = "pageList",method= RequestMethod.POST)
    @ResponseBody
    public ReType pageList(@RequestBody BeanSearch beanSearch) {
        ReType reType = new ReType();
        Page<Expert> tPage = PageHelper.startPage(beanSearch.getPagination().getPageNum(), beanSearch.getPagination().getPageSize());
        //还不对
        HashMap<String, Object> map = new HashMap<>();
        map.put("type",beanSearch.getKeyword());
        List<Expert> tList = (List<Expert>) expertService.listByMap(map);
        reType.setData(tList);
        reType.setTotal(tPage.getTotal());
        return reType;
    }
}
