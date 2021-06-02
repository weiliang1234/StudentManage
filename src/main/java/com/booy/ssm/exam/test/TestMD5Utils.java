package com.booy.ssm.exam.test;

import com.booy.ssm.exam.dao.StudentMapper;
import com.booy.ssm.exam.dao.UserDAO;
import com.booy.ssm.exam.pojo.Student;
import com.booy.ssm.exam.pojo.User;
import com.booy.ssm.exam.service.StudentService;
import com.booy.ssm.exam.service.impl.StudentServiceImpl;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * @author:wl
 * @Date:2021/5/6 20:03
 * @projectName:ssmdemo
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class TestMD5Utils {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    UserDAO userDAO;
    @Autowired
    StudentService studentService;

    @Test
    public void MD5(){
        System.out.println(DigestUtils.md5DigestAsHex(("66666" + 123456).getBytes()));
    }

    @Test
    public void Student() {
        //Student student = studentMapper.selectByPrimaryKey("1");
        //System.out.println(student);

        Student student1 = new Student();
        PageInfo<Student> userList = studentService.getStudentList(student1, 5, 5);
        System.out.println(userList);
    }

    @Test
    public void user() {
        User user = userDAO.getUserByAccount("a000001");
        System.out.println(user);
    }




}
