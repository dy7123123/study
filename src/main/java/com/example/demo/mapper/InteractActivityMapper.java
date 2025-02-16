package com.example.demo.mapper;

import com.example.demo.pojo.InteractActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface InteractActivityMapper {


    @Select("select * from interact_activity")
    public List<InteractActivity> list();
}
