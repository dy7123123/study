package com.dy.study.dto;

import com.dy.study.pojo.ActivityAward;
import com.dy.study.pojo.ActivityRule;
import com.dy.study.pojo.InteractActivity;
import lombok.Data;

import java.util.List;

@Data
public class CreateActivityRequest {
    private InteractActivity activity;
    private List<ActivityRule> rules;
    private List<ActivityAward> awards;
}
