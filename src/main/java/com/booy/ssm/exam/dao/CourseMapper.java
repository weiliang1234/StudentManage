package com.booy.ssm.exam.dao;

import com.booy.ssm.exam.pojo.Course;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseMapper {


    int deleteByPrimaryKey(Integer coursenumber);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer coursenumber);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    List<Course> getCoursesList(Course course);
}