package com.dy.study.task;

import com.dy.study.pojo.InteractActivity;
import com.dy.study.service.InteractActivityService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author dy
 * @date 2025/6/6
 * @description 定时任务类
 * 更新 未开始活动2->进行中3 进行中3->结束4
 */

@Component
@Slf4j
public class ActivityStatusScheduler {

    @Autowired
    private InteractActivityService interactActivityService;

    /**
     * 定时任务
     * 使用 XXL-JOB 进行定时调度，调度中心控制 cron 表达式
     */

    @XxlJob("updateActivityStatusJob")
    public void updateActivityStatusXxlJob() {
        log.info("XXL-JOB 任务启动：活动状态更新任务");

        try {
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            XxlJobHelper.log("执行时间：" + sdf.format(now));

            // 1. 更新未开始 -> 进行中
            log.info("开始更新未开始活动为进行中");
            List<InteractActivity> notStartedActivities = interactActivityService.getActivitiesByStatus(2);
            for (InteractActivity activity : notStartedActivities) {
                if (activity.getStartTime().before(now) && activity.getEndTime().after(now)) {
                    activity.setStatus(3);
                    activity.setModifiedTime(now);
                    interactActivityService.updateActivity(activity);
                    log.info("活动ID: {} 状态更新为进行中", activity.getId());
                }
            }

            // 2. 更新进行中 -> 已结束
            log.info("开始更新进行中活动为已结束");
            List<InteractActivity> ongoingActivities = interactActivityService.getActivitiesByStatus(3);
            for (InteractActivity activity : ongoingActivities) {
                if (activity.getEndTime().before(now)) {
                    activity.setStatus(4);
                    activity.setModifiedTime(now);
                    interactActivityService.updateActivity(activity);
                    log.info("活动ID: {} 状态更新为已结束", activity.getId());
                }
            }

            XxlJobHelper.log("活动状态更新任务执行成功");
        } catch (Exception e) {
            log.error("活动状态更新任务执行失败", e);
            XxlJobHelper.log("任务执行失败: " + e.getMessage());
            XxlJobHelper.handleFail("任务执行失败: " + e.getMessage());
        }
    }

}
