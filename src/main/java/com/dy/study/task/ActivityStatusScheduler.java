package com.dy.study.task;

import com.dy.study.pojo.InteractActivity;
import com.dy.study.service.InteractActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author dy
 * @date 2025/4/5
 * @description 定时任务类
 * 更新 未开始活动2->进行中3 进行中3->结束4
 */

@Component
@Slf4j
public class ActivityStatusScheduler {

    @Autowired
    private InteractActivityService interactActivityService;

    @Scheduled(fixedRate = 60000) // 每分钟执行一次
    public void updateActivityStatus(){
        log.info("定时任务开始执行，更新活动状态");
        // 更新未开始活动为进行中
        Date now = new Date();
        log.info("更新未开始活动为进行中");


        List<InteractActivity> activitiesByStatus = interactActivityService.getActivitiesByStatus(2);
        for (InteractActivity activity : activitiesByStatus) {
            // 判断未开始活动列表中的活动是否更新为进行中
            if(activity.getStartTime().before(now) && activity.getEndTime().after(now)){
                activity.setStatus(3); // 更新为进行中
                activity.setModifiedTime(now); // 修改时间
                interactActivityService.updateActivity(activity);
                log.info("活动ID: {} 状态更新为进行中", activity.getId());
            }
        }
        // 更新进行中的活动为结束
        log.info("更新进行中的活动为结束");
        List<InteractActivity> ongoingActivities = interactActivityService.getActivitiesByStatus(3);
        for (InteractActivity activity : ongoingActivities) {
            // 判断进行中活动列表中的活动是否更新为结束
            if(activity.getEndTime().before(now)){
                activity.setStatus(4); // 更新为结束
                activity.setModifiedTime(now); // 修改时间
                interactActivityService.updateActivity(activity);
                log.info("活动ID: {} 状态更新为结束", activity.getId());
            }
        }



    }

}
