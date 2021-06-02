package com.booy.ssm.exam.service.impl;

import com.booy.ssm.exam.dao.TeacherLoginMapper;
import com.booy.ssm.exam.dao.TeacherMapper;
import com.booy.ssm.exam.pojo.*;
import com.booy.ssm.exam.service.TeacherService;
import com.booy.ssm.exam.utils.AjaxResult;
import com.booy.ssm.exam.utils.DataUtils;
import com.booy.ssm.exam.utils.ExamConstants;
import com.booy.ssm.exam.utils.MD5Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author:wl
 * @Date:2021/5/13 17:10
 * @projectName:ssmdemo
 * @description:
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    TeacherLoginMapper teacherLoginMapper;

    @Override
    public PageInfo<Teacher> getTeacherList(Teacher teacher, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Teacher> teacherList = new ArrayList<>();
        List<Teacher> teachers1 = teacherMapper.getTeacherList(teacher);
        for (Teacher teachers2 : teachers1) {
            teachers2.setDateString(DataUtils.format(teachers2.getAdmissiondate()));
            teacherList.add(teachers2);
        }
        PageInfo<Teacher> teachers = new PageInfo<>(teacherList);
        return teachers;
    }

    @Override
    public Teacher selectByPrimaryKey(Integer jobnumber) {
        return teacherMapper.selectByPrimaryKey(jobnumber);
    }

    @Override
    public AjaxResult deleteStudent(Integer[] ids) {
        AjaxResult result = new AjaxResult();
        for(int id:ids){
            teacherMapper.deleteByPrimaryKey(id);
            teacherLoginMapper.deleteByPrimaryKey(id);
        }
        result.setStatus(true);
        return result;
    }

    @Override
    public AjaxResult addTeacher(Teacher teacher) {
        AjaxResult result = new AjaxResult();
        int Jobnumber = teacher.getJobnumber();
        if(teacherMapper.selectByPrimaryKey(teacher.getJobnumber()) == null) {
            if (DataUtils.SimpleDateFormat(teacher.getDateString()).getTime() < new Date().getTime()) {
                if (teacher.getPhone().length() == 11) {
                    teacher.setAdmissiondate(DataUtils.SimpleDateFormat(teacher.getDateString()));
                    teacherMapper.insert(teacher);
                    String password = DigestUtils.md5DigestAsHex(("666666" + 123456).getBytes());
                    TeacherLogin teacherLogin = new TeacherLogin(Jobnumber,teacher.getName(),password,1,"666666");
                    teacherMapper.insertTeacherLogin(teacherLogin);
                    result.setStatus(true);
                    return result;
                }else {
                    result.setStatus(false);
                    result.setMessage("电话号码必须为11位!");
                    return result;
                }
            }else {
                result.setStatus(false);
                result.setMessage("日期不能大于当前日期!");
                return result;
            }
        }
        result.setStatus(false);
        result.setMessage("该教师已存在！");
        return result;
    }

    @Override
    public AjaxResult updateTeacher(Teacher teacher) {
        AjaxResult result = new AjaxResult();
        try {
            teacher.setAdmissiondate(DataUtils.SimpleDateFormat(teacher.getDateString()));
            teacherMapper.updateTeacher(teacher);
            result.setStatus(true);
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false);
            return result;
        }
    }

    @Override
    public Teacher selectByPrimaryID(Integer id) {
        return teacherMapper.selectByPrimaryID(id);
    }

    @Override
    public TeacherLogin dologin(int account, String password) {
        TeacherLogin teacherLogin = teacherMapper.getTeacherByAccount(account);
        //Teacher teacher = teacherMapper.selectByPrimaryKey(account);
        String loginMD5 = MD5Utils.getLoginMD5(teacherLogin.getSalt(), password);
        if(teacherLogin == null || !teacherLogin.getPassword().equals(loginMD5)){
            return null;//账号或密码错误
        }
        return teacherLogin;
    }
}
