package com.cnki.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnki.entity.BeanSearch;
import com.cnki.entity.Expert;
import com.cnki.service.ExpertService;
import com.cnki.utils.ReType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义标题别名
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("type", "类型");
        writer.addHeaderAlias("createTime", "时间");
        writer.write(list, true);
        ServletOutputStream out = null;
        try {
            response.setContentType("application/vnd.ms-excel");
            String name = new String("测试".getBytes("gb2312"), "ISO_8859_1");
            response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xls");
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
        IoUtil.close(out);
    }
}