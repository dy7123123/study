package com.dy.study.service.Impl;

import com.dy.study.dto.ActivityExportDTO;
import com.dy.study.mapper.ActivityAwardMapper;
import com.dy.study.mapper.ActivityRuleMapper;
import com.dy.study.mapper.InteractActivityMapper;
import com.dy.study.pojo.*;
import com.dy.study.service.InteractActivityService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    /**
     * 导出互动营销活动数据为 Excel 文件
     * @param response HTTP 响应
     * @throws IOException IO异常
     */
    @Override
    public void exportActivityData(HttpServletResponse response) throws IOException {
        log.info("开始查询互动营销活动数据...");
        List<ActivityExportDTO> activities = interactActivityMapper.getAllActivityInfo();
        log.info("查询到 {} 条数据", activities.size());

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Activity Data");
        Row headerRow = sheet.createRow(0);

        String[] headers = {"活动名称", "开始时间", "结束时间", "状态", "邀请成功人数", "创建人", "创建时间"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(getHeaderCellStyle(workbook));
        }

        int rowNum = 1;
        for (ActivityExportDTO activity : activities) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(activity.getActivityName());
            row.createCell(1).setCellValue(activity.getStartTime().toString());
            row.createCell(2).setCellValue(activity.getEndTime().toString());
            row.createCell(3).setCellValue(activity.getStatus());
            row.createCell(4).setCellValue(activity.getInviteSuccessCount());
            row.createCell(5).setCellValue(activity.getCreator());
            row.createCell(6).setCellValue(activity.getCreatedTime().toString());
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=activity_export.xlsx");

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
            workbook.close();
            log.info("Excel 文件导出成功！");
        } catch (IOException e) {
            log.error("导出 Excel 失败：", e);
            throw e;
        }
    }

    /**
     * 获取Excel表头样式
     * @param workbook Excel 工作簿
     * @return 单元格样式
     */
    private CellStyle getHeaderCellStyle(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        return headerStyle;
    }

}
