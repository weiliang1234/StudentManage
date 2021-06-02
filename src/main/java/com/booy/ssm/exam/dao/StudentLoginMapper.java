package com.booy.ssm.exam.dao;

import com.booy.ssm.exam.pojo.StudentLogin;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentLoginMapper {

    int deleteByPrimaryKey(Integer stuid);

    int insert(StudentLogin record);

    int insertSelective(StudentLogin record);

    StudentLogin selectByPrimaryKey(Integer stuid);

    int updateByPrimaryKeySelective(StudentLogin record);

    int updateByPrimaryKey(StudentLogin record);
}