package com.dy.study.controller;


import com.dy.study.pojo.PageBean;
import com.dy.study.pojo.Result;
import com.dy.study.service.InteractActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class InteractActivityController {
    @Autowired
    private InteractActivityService interactActivityService;

    @GetMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分业查询：page:{},pageSize:{}", page, pageSize);
        PageBean pageBean =interactActivityService.page(page, pageSize);
        return Result.success(pageBean);

    }

}
