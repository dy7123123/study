package com.dy.study.mapper;

import com.dy.study.pojo.InteractActivity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InteractActivityMapper {

    @Select("select * from interact_activity")
    public List<InteractActivity> list();

    @Insert("INSERT INTO interact_activity (activity_id, activity_name, activity_type, start_time, end_time, status, activity_terms, creator, created_time, modified_time) " +
            "VALUES (#{activityId}, #{activityName}, #{activityType}, #{startTime}, #{endTime}, #{status}, #{activityTerms}, #{creator}, #{createdTime}, #{modifiedTime})")
    void insert(InteractActivity activity);
}
