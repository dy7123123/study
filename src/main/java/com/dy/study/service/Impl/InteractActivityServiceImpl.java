package com.dy.study.service.Impl;

import com.dy.study.controller.mapper.InteractActivityMapper;
import com.dy.study.pojo.InteractActivity;
import com.dy.study.pojo.PageBean;
import com.dy.study.service.InteractActivityService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteractActivityServiceImpl implements InteractActivityService {

    @Autowired
    private InteractActivityMapper interactActivityMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<InteractActivity> list = interactActivityMapper.list();
        Page<InteractActivity> p = (Page<InteractActivity>) list;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }
}
