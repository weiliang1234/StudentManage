package com.booy.ssm.exam.dao;

import com.booy.ssm.exam.pojo.TeacherLogin;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherLoginMapper {

    int deleteByPrimaryKey(Integer jobnumber);

    int insert(TeacherLogin record);

    int insertSelective(TeacherLogin record);

    TeacherLogin selectByPrimaryKey(Integer jobnumber);

    int updateByPrimaryKeySelective(TeacherLogin record);

    int updateByPrimaryKey(TeacherLogin record);
}