package com.dy.study.mapper;

import com.dy.study.pojo.ActivityRule;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityRuleMapper {

    @Insert("INSERT INTO activity_rule (activity_id, rule_code, rule, created_time, modified_time) " +
            "VALUES (#{activityId}, #{ruleCode}, #{rule}, #{createdTime}, #{modifiedTime})")
    void insert(ActivityRule activityRule);

}
