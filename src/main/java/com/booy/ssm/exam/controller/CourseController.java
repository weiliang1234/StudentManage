package com.booy.ssm.exam.controller;

import com.booy.ssm.exam.pojo.Course;
import com.booy.ssm.exam.pojo.Student;
import com.booy.ssm.exam.service.CourseService;
import com.booy.ssm.exam.utils.AjaxResult;
import com.booy.ssm.exam.utils.TableData;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:wl
 * @Date:2021/5/13 10:08
 * @projectName:ssmdemo
 * @description:
 */
@Controller
@RequestMapping("/sys/course.html")
public class CourseController {

    @Autowired
    CourseService courseService;

    @RequestMapping
    public String course(){
        return "course";
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
    public AjaxResult edit(Course course){
        //添加
        if(courseService.selectByPrimaryKey(course.getCoursenumber()) == null){
            AjaxResult result = courseService.addCourse(course);
            return result;
        }else{//修改
            AjaxResult result = courseService.updateCourse(course);
            System.out.println(course.getTeacher());
            return result;
        }
    }

    @RequestMapping(params = "act=delete")
    @ResponseBody
    public AjaxResult deleteUser(Integer[] ids){
        try {
            AjaxResult result = courseService.deleteStudent(ids);
            result.setStatus(true);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            AjaxResult result = new AjaxResult();
            result.setStatus(false);
            result.setMessage("删除失败！");
            return result;
        }
    }
}
