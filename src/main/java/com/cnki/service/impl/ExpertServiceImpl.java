package com.cnki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cnki.entity.Expert;
import com.cnki.mapper.ExpertMapper;
import com.cnki.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author li-lb
 */
@Service
public class ExpertServiceImpl extends ServiceImpl<ExpertMapper, Expert> implements ExpertService {
    @Autowired
    private ExpertMapper expertMapper;


}
