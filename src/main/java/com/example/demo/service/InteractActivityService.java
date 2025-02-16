package com.example.demo.service;


import com.example.demo.pojo.PageBean;

public interface InteractActivityService {
    //分页查询
    PageBean page(Integer page, Integer pageSize);
}
