package com.booy.ssm.exam.service;

import com.booy.ssm.exam.pojo.Course;
import com.booy.ssm.exam.pojo.Student;
import com.booy.ssm.exam.utils.AjaxResult;
import com.github.pagehelper.PageInfo;

/**
 * @author:wl
 * @Date:2021/5/13 10:27
 * @projectName:ssmdemo
 * @description:
 */
public interface CourseService {

    PageInfo<Course> getCourseList(Course course, int pageNum, int pageSize);

    Course selectByPrimaryKey(Integer coursenumber);

    AjaxResult addCourse(Course course);

    AjaxResult updateCourse(Course course);

    AjaxResult deleteStudent(Integer[] coursenumber);
}
