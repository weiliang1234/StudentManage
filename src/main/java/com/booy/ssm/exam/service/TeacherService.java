package com.booy.ssm.exam.service;

import com.booy.ssm.exam.pojo.Student;
import com.booy.ssm.exam.pojo.Teacher;
import com.booy.ssm.exam.pojo.TeacherLogin;
import com.booy.ssm.exam.utils.AjaxResult;
import com.github.pagehelper.PageInfo;

/**
 * @author:wl
 * @Date:2021/5/13 17:07
 * @projectName:ssmdemo
 */
public interface TeacherService {
    PageInfo<Teacher> getTeacherList(Teacher teacher, int page, int limit);

    Teacher selectByPrimaryKey(Integer jobnumber);

    AjaxResult deleteStudent(Integer[] ids);

    AjaxResult addTeacher(Teacher teacher);

    AjaxResult updateTeacher(Teacher teacher);

    Teacher selectByPrimaryID(Integer id);

    TeacherLogin dologin(int account1, String password);
}
