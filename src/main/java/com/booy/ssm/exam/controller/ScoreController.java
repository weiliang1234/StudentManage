package com.booy.ssm.exam.controller;

import com.booy.ssm.exam.pojo.Course;
import com.booy.ssm.exam.pojo.Score;
import com.booy.ssm.exam.pojo.StudentLogin;
import com.booy.ssm.exam.service.ScoreService;
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
@RequestMapping("/sys/score.html")
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    @RequestMapping
    public String score(){
        return "score";
    }

    @RequestMapping(params = "act=table")
    @ResponseBody
    public TableData table(Score score, int page, int limit, HttpSession session){
        StudentLogin studentLogin = (StudentLogin) session.getAttribute("student");
        Integer stuid = studentLogin.getStuId();
        PageInfo<Course> pageInfo = scoreService.getScoreList(score, page, limit, stuid);
        TableData tableData = new TableData(pageInfo.getTotal(), pageInfo.getList());
        return tableData;
    }
}
