package com.booy.ssm.exam.service.impl;

import com.booy.ssm.exam.dao.CourseMapper;
import com.booy.ssm.exam.dao.TeacherMapper;
import com.booy.ssm.exam.pojo.Course;
import com.booy.ssm.exam.pojo.Student;
import com.booy.ssm.exam.pojo.Teacher;
import com.booy.ssm.exam.service.CourseService;
import com.booy.ssm.exam.service.TeacherService;
import com.booy.ssm.exam.utils.AjaxResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wl
 * @Date:2021/5/13 10:28
 * @projectName:ssmdemo
 * @description:
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public PageInfo<Course> getCourseList(Course course, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Course> courseList = new ArrayList<>();
        List<Course> course1 = courseMapper.getCoursesList(course);
        for (Course course2 : course1) {
            Teacher teacher = teacherMapper.selectByPrimaryKey(course2.getTeacher());
            course2.setTeachername(teacher.getName());
            courseList.add(course2);
        }
        PageInfo<Course> courses = new PageInfo<>(courseList);
        return courses;
    }

    @Override
    public Course selectByPrimaryKey(Integer coursenumber) {
        return courseMapper.selectByPrimaryKey(coursenumber);
    }

    @Override
    public AjaxResult addCourse(Course course) {
        AjaxResult result = new AjaxResult();
        if(courseMapper.selectByPrimaryKey(course.getCoursenumber()) == null){
            courseMapper.insert(course);
            result.setStatus(true);
            return result;
        }
        result.setStatus(false);
        result.setMessage("该课程已存在！");
        return result;
    }

    @Override
    public AjaxResult updateCourse(Course course) {
        AjaxResult result = new AjaxResult();
        try {
            courseMapper.updateByPrimaryKeySelective(course);
            result.setStatus(true);
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false);
            return result;
        }
    }

    @Override
    public AjaxResult deleteStudent(Integer[] coursenumber) {
        AjaxResult result = new AjaxResult();
        for (Integer coursenumbers : coursenumber) {
            courseMapper.deleteByPrimaryKey(coursenumbers);
        }
        result.setStatus(true);
        return result;
    }
}
