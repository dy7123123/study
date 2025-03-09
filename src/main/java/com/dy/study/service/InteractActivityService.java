package com.dy.study.service;

import com.dy.study.pojo.ActivityAward;
import com.dy.study.pojo.ActivityRule;
import com.dy.study.pojo.InteractActivity;
import com.dy.study.pojo.PageBean;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface InteractActivityService {
    PageBean page(Integer page, Integer pageSize);

    void createActivity(InteractActivity activity, List<ActivityRule> rules, List<ActivityAward> awards);

    void exportActivityData(HttpServletResponse response) throws IOException;
}
