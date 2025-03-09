package com.dy.study.mapper;

import com.dy.study.dto.ActivityExportDTO;
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

    @Select("SELECT ia.activity_name, ia.start_time, ia.end_time, ia.status, " +
            "COUNT(ar.activity_id) AS invite_success_count, ia.creator, ia.created_time " +
            "FROM interact_activity ia " +
            "LEFT JOIN activity_rule ar ON ia.activity_id = ar.activity_id " +
            "GROUP BY ia.activity_id, ia.activity_name, ia.start_time, ia.end_time, ia.status, ia.creator, ia.created_time")
    List<ActivityExportDTO> getAllActivityInfo();

}
