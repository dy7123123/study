package com.dy.study.controller.mapper;

import com.dy.study.pojo.InteractActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InteractActivityMapper {

    @Select("select * from interact_activity")
    public List<InteractActivity> list();
}
