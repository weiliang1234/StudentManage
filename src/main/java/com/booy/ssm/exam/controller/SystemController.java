package com.booy.ssm.exam.controller;

import com.booy.ssm.exam.pojo.*;
import com.booy.ssm.exam.service.MenuService;
import com.booy.ssm.exam.service.StudentService;
import com.booy.ssm.exam.service.TeacherService;
import com.booy.ssm.exam.service.UserService;
import com.booy.ssm.exam.utils.ExamConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SystemController {
    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    MenuService menuService;

    //默认登录页
    @RequestMapping("login.html")
    public String login(){
        return "login";
    }

    @RequestMapping("error.html")
    public String error(){
        return "error";
    }

    //主页菜单树
    @RequestMapping("index.html")
    public String index(Model model,HttpSession session){
        MQ mq = (MQ) session.getAttribute(ExamConstants.SESSION_USER);
        List<Menu> menuList = menuService.getUserMenuList(mq.getId());
        model.addAttribute("menuList",menuList);
        session.setAttribute(ExamConstants.USER_MENU,menuList);//把菜单树存放在session里
        return "index";
    }

    //用户登录
    @RequestMapping("dologin.html")
    public String dologin(String account, String password, int role, Model model, HttpSession session){
        if (role == 0) {
            //学生登录
            int account1 = Integer.parseInt(account);
            StudentLogin studentLogin = studentService.dologin(account1, password);
            if(studentLogin == null){
                model.addAttribute("message","用户名或密码错误！");
                return "login";
            }
            MQ mq = userService.findMQbyflag(studentLogin.getFlag());
            mq.setNickname(studentLogin.getStuname());
            session.setAttribute("student", studentLogin);
            session.setAttribute(ExamConstants.SESSION_USER,mq);
        }
        //教师登录
        if (role == 1) {
            int account1 = Integer.parseInt(account);
            TeacherLogin teacherLogin = teacherService.dologin(account1, password);
            if(teacherLogin == null){
                model.addAttribute("message","用户名或密码错误！");
                return "login";
            }
            MQ mq = userService.findMQbyflag(teacherLogin.getFlag());
            mq.setNickname(teacherLogin.getName());
            session.setAttribute(ExamConstants.SESSION_USER,mq);
            session.setAttribute("teacher", teacherLogin);
        }
        //管理员登录
        if (role == 2) {
            User user = userService.dologin(account, password);
            if(user == null){
                model.addAttribute("message","用户名或密码错误！");
                return "login";
            }
            MQ mq = userService.findMQbyflag(user.getFlag());
            mq.setNickname(user.getNickname());
            session.setAttribute(ExamConstants.SESSION_USER,mq);
        }
        return "redirect:index.html";
    }
    //用户注销
    @RequestMapping("logout.html")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

}
