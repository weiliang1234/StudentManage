package com.booy.ssm.exam.dao;

import com.booy.ssm.exam.pojo.Teacher;

import java.util.List;

import com.booy.ssm.exam.pojo.TeacherLogin;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {

    int deleteByPrimaryKey(Integer jobnumber);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer jobnumber);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    List<Teacher> getTeacherList(Teacher teacher);

    void insertTeacherLogin(TeacherLogin teacherLogin);

    void updateTeacher(Teacher teacher);

    Teacher selectByPrimaryID(Integer id);

    TeacherLogin getTeacherByAccount(int account);
}