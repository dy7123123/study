package com.dy.study.controller;


import com.dy.study.dto.CreateActivityRequest;
import com.dy.study.pojo.PageBean;
import com.dy.study.pojo.Result;
import com.dy.study.service.InteractActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/activity")

public class InteractActivityController {
    @Autowired
    private InteractActivityService interactActivityService;

    // 分页查询
    @GetMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分业查询：page:{},pageSize:{}", page, pageSize);
        PageBean pageBean =interactActivityService.page(page, pageSize);
        return Result.success(pageBean);

    }

    // 创建活动
    @PostMapping("/create")
    public Result createActivity(@RequestBody CreateActivityRequest request) {
        log.info("接收到创建活动请求: {}", request);
        try {
            // 调用服务层进行活动创建
            interactActivityService.createActivity(request.getActivity(), request.getRules(), request.getAwards());
            log.info("活动创建成功: {}", request.getActivity().getActivityName());
            return Result.success("活动创建成功");
        } catch (Exception e) {
            log.error("活动创建失败: {}", e.getMessage(), e);
            return Result.error("活动创建失败");
        }
    }


}
