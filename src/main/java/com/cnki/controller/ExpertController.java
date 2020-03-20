package com.cnki.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnki.entity.BeanSearch;
import com.cnki.entity.Expert;
import com.cnki.service.ExpertService;
import com.cnki.utils.ExcelUtils;
import com.cnki.utils.ReType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
     *
     * @param beanSearch
     * @return
     */
    @RequestMapping(value = "pageList", method = RequestMethod.POST)
    @ResponseBody
    public ReType pageList(@RequestBody BeanSearch beanSearch) {
        ReType reType = new ReType();
        Page<Expert> page = new Page<>(beanSearch.getPagination().getPageNum(), beanSearch.getPagination().getPageSize());
        QueryWrapper<Expert> wrapper = new QueryWrapper();
        Expert expert = new Expert();
        expert.setType(beanSearch.getKeyword());
        wrapper.setEntity(expert);
        IPage<Expert> mapIPage = expertService.page(page, wrapper);
        reType.setData(mapIPage.getRecords());
        reType.setTotal(mapIPage.getTotal());
        return reType;
    }

    @RequestMapping("/export")
    @ResponseBody
    public void export(HttpServletResponse response) {
        List<Expert> list = expertService.list();
        Map<String, String> map = new LinkedHashMap<>(3);
        map.put("name","姓名");
        map.put("type","类型");
        map.put("createTime","时间");
        try {
            ExcelUtils.export(response,"列表",list,map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}