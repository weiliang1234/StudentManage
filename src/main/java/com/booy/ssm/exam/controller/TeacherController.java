package com.booy.ssm.exam.controller;

import com.booy.ssm.exam.pojo.Student;
import com.booy.ssm.exam.pojo.Teacher;
import com.booy.ssm.exam.service.StudentService;
import com.booy.ssm.exam.service.TeacherService;
import com.booy.ssm.exam.utils.AjaxResult;
import com.booy.ssm.exam.utils.DataUtils;
import com.booy.ssm.exam.utils.TableData;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author:wl
 * @Date:2021/5/8 11:14
 * @projectName:ssmdemo
 * @description:
 */
@Controller
@RequestMapping("/sys/teacher.html")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping
    public String teacher(){
        return "teacher";
    }

    @RequestMapping(params = "act=table")
    @ResponseBody
    public TableData table(Teacher teacher, int page, int limit){
        PageInfo<Teacher> pageInfo = teacherService.getTeacherList(teacher, page, limit);
        TableData tableData = new TableData(pageInfo.getTotal(), pageInfo.getList());
        return tableData;
    }

    @RequestMapping(params = "act=edit")
    @ResponseBody
    public AjaxResult edit(Teacher teacher){
        //添加
        if(teacherService.selectByPrimaryID(teacher.getId()) == null){
            AjaxResult result = teacherService.addTeacher(teacher);
            return result;
        }else{//修改
            AjaxResult result = teacherService.updateTeacher(teacher);
            return result;
        }
    }

    @RequestMapping(params = "act=delete")
    @ResponseBody
    public AjaxResult deleteUser(Integer[] ids){
        try {
            AjaxResult result = teacherService.deleteStudent(ids);
            result.setStatus(true);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            AjaxResult result = new AjaxResult();
            result.setStatus(false);
            result.setMessage("删除失败！");
            return result;
        }
    }
}
