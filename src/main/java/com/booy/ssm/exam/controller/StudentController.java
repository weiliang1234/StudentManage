package com.booy.ssm.exam.controller;

import com.booy.ssm.exam.pojo.Student;
import com.booy.ssm.exam.service.StudentService;
import com.booy.ssm.exam.utils.AjaxResult;
import com.booy.ssm.exam.utils.TableData;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:wl
 * @Date:2021/5/8 11:14
 * @projectName:ssmdemo
 * @description:
 */
@Controller
@RequestMapping("/sys/student.html")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping
    public String student(){
        return "student";
    }

    @RequestMapping(params = "act=table")
    @ResponseBody
    public TableData table(Student student, int page, int limit){
        PageInfo<Student> pageInfo = studentService.getStudentList(student, page, limit);
        TableData tableData = new TableData(pageInfo.getTotal(), pageInfo.getList());
        return tableData;
    }

    @RequestMapping(params = "act=edit")
    @ResponseBody
    public AjaxResult edit(Student student){
        //添加
        if(studentService.selectByPrimaryKey(student.getStuId()) == null){
            AjaxResult result = studentService.addStudent(student);
            return result;
        }else{//修改
            AjaxResult result = studentService.updateStudent(student);
            return result;
        }
    }

    @RequestMapping(params = "act=delete")
    @ResponseBody
    public AjaxResult deleteUser(Integer[] ids){
        try {
            AjaxResult result = studentService.deleteStudent(ids);
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
