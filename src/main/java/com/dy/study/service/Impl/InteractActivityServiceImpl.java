package com.dy.study.service.Impl;

import com.dy.study.mapper.ActivityAwardMapper;
import com.dy.study.mapper.ActivityRuleMapper;
import com.dy.study.mapper.InteractActivityMapper;
import com.dy.study.pojo.ActivityAward;
import com.dy.study.pojo.ActivityRule;
import com.dy.study.pojo.InteractActivity;
import com.dy.study.pojo.PageBean;
import com.dy.study.service.InteractActivityService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class InteractActivityServiceImpl implements InteractActivityService {

    @Autowired
    private InteractActivityMapper interactActivityMapper;
    @Autowired
    private ActivityRuleMapper activityRuleMapper;
    @Autowired
    private ActivityAwardMapper activityAwardMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<InteractActivity> list = interactActivityMapper.list();
        Page<InteractActivity> p = (Page<InteractActivity>) list;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    @Transactional
    public void createActivity(InteractActivity activity, List<ActivityRule> rules, List<ActivityAward> awards) {
        try {
            // 插入活动主表
            log.info("插入活动: {}", activity);
            interactActivityMapper.insert(activity);

            // 插入规则表
            for (ActivityRule rule : rules) {
                rule.setActivityId(activity.getActivityId());
                log.info("插入规则: {}", rule);
                activityRuleMapper.insert(rule);
            }

            // 插入奖项表
            for (ActivityAward award : awards) {
                award.setActivityId(activity.getActivityId());
                log.info("插入奖项: {}", award);
                activityAwardMapper.insert(award);
            }
        } catch (Exception e) {
            log.error("创建活动过程中发生错误: {}", e.getMessage(), e);
            throw new RuntimeException("创建活动失败", e);
        }
    }
}
