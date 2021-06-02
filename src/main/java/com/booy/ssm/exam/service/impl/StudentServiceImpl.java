package com.booy.ssm.exam.service.impl;

import com.booy.ssm.exam.dao.StudentMapper;
import com.booy.ssm.exam.pojo.Student;
import com.booy.ssm.exam.pojo.StudentLogin;
import com.booy.ssm.exam.pojo.User;
import com.booy.ssm.exam.service.StudentService;
import com.booy.ssm.exam.utils.AjaxResult;
import com.booy.ssm.exam.utils.ExamConstants;
import com.booy.ssm.exam.utils.MD5Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author:wl
 * @Date:2021/5/8 10:05
 * @projectName:ssmdemo
 * @description:
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageInfo<Student> getStudentList(Student student, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Student> students = new PageInfo<>(studentMapper.getStudentList(student));
        return students;
    }

    @Override
    public AjaxResult addStudent(Student student) {
        AjaxResult result = new AjaxResult();
        int stuId = student.getStuId();
        if(studentMapper.selectByPrimaryKey(student.getStuId()) == null){
            studentMapper.insert(student);
            String password = DigestUtils.md5DigestAsHex(("666666" + 123456).getBytes());
            StudentLogin studentLogin = new StudentLogin(stuId,student.getStuname(),password,0,"666666");
            studentMapper.insertStudentLogin(studentLogin);
            result.setStatus(true);
            return result;
        }
        result.setStatus(false);
        result.setMessage("该学生已存在！");
        return result;
    }

    @Override
    public AjaxResult updateStudent(Student student) {
        AjaxResult result = new AjaxResult();
        try {
            studentMapper.updateByPrimaryKeySelective(student);
            result.setStatus(true);
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false);
            return result;
        }
    }

    @Override
    public AjaxResult deleteStudent(Integer[] ids) {
        AjaxResult result = new AjaxResult();
        for (Integer stuId : ids) {
            studentMapper.deleteByPrimaryKey(stuId);
        }
        result.setStatus(true);
        return result;
    }

    @Override
    public Student selectByPrimaryKey(Integer stuid) {
        return studentMapper.selectByPrimaryKey(stuid);
    }

    @Override
    public StudentLogin dologin(Integer account, String password) {
        StudentLogin studentLogin = studentMapper.getStudentByAccount(account);
        Student student = studentMapper.selectByPrimaryKey(account);
        String loginMD5 = MD5Utils.getLoginMD5(studentLogin.getSalt(), password);
        if(studentLogin == null || !studentLogin.getPassword().equals(loginMD5) || !student.getStatus().equals(ExamConstants.STUDENT_STATUS_DELETE)){
            return null;//账号或密码错误
        }
        return studentLogin;
    }
}
