package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//分页查询结果封装类
public class PageBean {
    private Long total; //总记录数
    private List rows; //当前页数据
    public PageBean() {
    }
    public PageBean(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public List getRows() {
        return rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public void setTotal(Long total) {
        this.total = total;
    }


}
