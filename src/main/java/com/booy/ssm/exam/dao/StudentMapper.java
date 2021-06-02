package com.booy.ssm.exam.dao;

import com.booy.ssm.exam.pojo.Student;

import java.util.List;

import com.booy.ssm.exam.pojo.StudentLogin;
import com.booy.ssm.exam.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {

    int deleteByPrimaryKey(Integer stuId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer stuid);

    List<Student> getStudentList(Student student);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    //Student selectByAccount(String account, String password);

    StudentLogin getStudentByAccount(Integer account);

    void insertStudentLogin(StudentLogin studentLogin);

    Student getStudentList2(Student student1);
}