package com.booy.ssm.exam.controller;

import com.booy.ssm.exam.pojo.Course;
import com.booy.ssm.exam.pojo.StudentLogin;
import com.booy.ssm.exam.service.CourseService;
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
 * @Date:2021/5/13 10:08
 */
@Controller
@RequestMapping("/sys/SelectCourse.html")
public class SelectCourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ScoreService scoreService;

    @RequestMapping
    public String course() {
        return "SelectCourse";
    }

    @RequestMapping(params = "act=table")
    @ResponseBody
    public TableData table(Course course, int page, int limit){
        PageInfo<Course> pageInfo = courseService.getCourseList(course, page, limit);
        TableData tableData = new TableData(pageInfo.getTotal(), pageInfo.getList());
        return tableData;
    }

    @RequestMapping(params = "act=edit")
    @ResponseBody
    public AjaxResult edit(Course course, HttpSession session) {
        AjaxResult result = null;
        Integer i = 0;
        Integer total = course.getTotal();
        StudentLogin student = (StudentLogin) session.getAttribute("student");
        Integer stuid = student.getStuId();
        Integer coursenumber = course.getCoursenumber();
        if (total > 0) {
            result = scoreService.insertScoreCourseNumber(stuid, coursenumber);
            total--;
            i++;
            course.setYixuan(i);
            course.setShengyu(100-i);
        }
        return result;
    }
}
