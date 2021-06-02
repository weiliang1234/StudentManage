package com.booy.ssm.exam.controller;

import com.booy.ssm.exam.pojo.*;
import com.booy.ssm.exam.service.ScoreService;
import com.booy.ssm.exam.utils.AjaxResult;
import com.booy.ssm.exam.utils.TableData;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author:wl
 * @Date:2021/5/15 16:36
 * @projectName:ssmdemo
 * @description:
 */
@Controller
@RequestMapping("/sys/teacher/score.html")
public class TeacherScoreController {

    @Autowired
    ScoreService scoreService;

    @RequestMapping
    public String score(){
        return "TeacherScore";
    }

    @RequestMapping(params = "act=table")
    @ResponseBody
    public TableData table(int page, int limit, HttpSession session, Student student){
        TableData tableData = null;
        if (student.getStuId() == null && (student.getStuname() == null || "".equals(student.getStuname()))) {
            TeacherLogin teacherLogin = (TeacherLogin) session.getAttribute("teacher");
            Integer jobnumber = teacherLogin.getJobnumber();
            PageInfo<Student> pageInfo = scoreService.getScoreList2(page, limit, jobnumber);
            tableData = new TableData(pageInfo.getTotal(), pageInfo.getList());
        }else {
            TeacherLogin teacherLogin = (TeacherLogin) session.getAttribute("teacher");
            Integer jobnumber = teacherLogin.getJobnumber();
            PageInfo<Student> pageInfo = scoreService.getScoreList3(page, limit, jobnumber, student);
            tableData = new TableData(pageInfo.getTotal(), pageInfo.getList());
        }
        return tableData;
    }

    @RequestMapping(params = "act=edit")
    @ResponseBody
    public AjaxResult edit(Integer score, Integer stuId, Integer coursenumber){
        Score score1 = new Score();
        score1.setScore(score);
        score1.setStuid(stuId);
        score1.setCoursenumber(coursenumber);
        //添加
        if(scoreService.findscorebyStuId(stuId) == null){
            AjaxResult result = scoreService.addScore(score1);
            return result;
        }else{//修改
            AjaxResult result = scoreService.addScore(score1);
            return result;
        }
    }
}
