package com.booy.ssm.exam.service;

import com.booy.ssm.exam.pojo.Student;
import com.booy.ssm.exam.pojo.StudentLogin;
import com.booy.ssm.exam.utils.AjaxResult;
import com.github.pagehelper.PageInfo;

/**
 * @author:wl
 * @Date:2021/5/8 10:05
 * @projectName:ssmdemo
 * @description:
 */
public interface StudentService {
    /**
     * 分页查询
     * @param student
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Student> getStudentList(Student student, int pageNum, int pageSize);

    /**
     * 添加学生
     * @param student
     * @return
     */
    AjaxResult addStudent(Student student);

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    AjaxResult updateStudent(Student student);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    AjaxResult deleteStudent(Integer[] ids);

    /**
     * 根据主键查询
     * @param stuid
     * @return
     */
    Student selectByPrimaryKey(Integer stuid);

    StudentLogin dologin(Integer account, String password);
}
