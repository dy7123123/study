package com.example.demo.controller;

import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Result;
import com.example.demo.service.InteractActivityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class InteractActivityController {

    @Autowired
    private InteractActivityService interactActivityService;

    @GetMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean pageBean =interactActivityService.page(page, pageSize);
        return Result.success(pageBean);

    }
}
