package com.dy.study.mapper;


import com.dy.study.pojo.ActivityAward;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityAwardMapper {

    @Insert("INSERT INTO activity_award (activity_id, award_type, ref_id, created_time, modified_time) " +
            "VALUES (#{activityId}, #{awardType}, #{refId}, #{createdTime}, #{modifiedTime})")
    void insert(ActivityAward activityAward);

}

