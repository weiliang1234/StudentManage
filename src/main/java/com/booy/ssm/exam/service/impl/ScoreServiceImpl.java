package com.booy.ssm.exam.service.impl;

import com.booy.ssm.exam.dao.CourseMapper;
import com.booy.ssm.exam.dao.ScoreMapper;
import com.booy.ssm.exam.dao.StudentMapper;
import com.booy.ssm.exam.pojo.Course;
import com.booy.ssm.exam.pojo.Score;
import com.booy.ssm.exam.pojo.ScoreKey;
import com.booy.ssm.exam.pojo.Student;
import com.booy.ssm.exam.service.ScoreService;
import com.booy.ssm.exam.utils.AjaxResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author:wl
 * @Date:2021/5/15 16:38
 * @projectName:ssmdemo
 * @description:
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    ScoreMapper scoreMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    StudentMapper studentMapper;

    @Override
    public PageInfo<Course> getScoreList(Score score, int page, int limit, @Param("stuid") int stuid) {
        PageHelper.startPage(page, limit);
        List<Course> courses = new ArrayList<>();
        List<Score> scoretList = scoreMapper.getScoretList(score, stuid);
        for (Score score1 : scoretList) {
            Course course = courseMapper.selectByPrimaryKey(score1.getCoursenumber());
            course.setScore(score1.getScore());
            courses.add(course);
        }
        PageInfo<Course> scores = new PageInfo<>(courses);
        return scores;
    }

    @Override
    public PageInfo<Student> getScoreList2(int page, int limit, @Param("jobnumber")Integer jobnumber) {
        PageHelper.startPage(page, limit);
        List<Student> students = new ArrayList<>();
        List<Course> courses = new ArrayList<>();
        courses = scoreMapper.getCourse(jobnumber);

        List<Integer> datas = new ArrayList<>();

        for (Course course : courses) {
            datas = scoreMapper.selectByjobnumber(course.getCoursenumber());
            for (Integer data : datas) {
                Student student = studentMapper.selectByPrimaryKey(data);
                student.setCoursenumber(course.getCoursenumber());
                student.setCoursename(course.getCoursename());
                student.setCoursecategory(course.getCoursecategory());
                ScoreKey scoreKey = new ScoreKey();
                scoreKey.setStuid(data);
                scoreKey.setCoursenumber(course.getCoursenumber());
                student.setScore(scoreMapper.selectByPrimaryKey(scoreKey));
                students.add(student);
            }
        }
        PageInfo<Student> studentList = new PageInfo<>(students);
        return studentList;
    }

    @Override
    public Integer findscorebyStuId(Integer stuId) {
        return scoreMapper.findscorebyStuId(stuId);
    }

    @Override
    public AjaxResult addScore(Score score) {
        AjaxResult result = new AjaxResult();
        scoreMapper.updateByPrimaryKeySelective(score);
        result.setStatus(true);
        return result;
    }

    @Override
    public PageInfo<Student> getScoreList3(int page, int limit, Integer jobnumber, Student student1) {
        PageHelper.startPage(page, limit);
        List<Student> students = new ArrayList<>();
        List<Student> students2 = new ArrayList<>();
        List<Course> courses = new ArrayList<>();
        courses = scoreMapper.getCourse(jobnumber);

        List<Integer> datas = new ArrayList<>();

        for (Course course : courses) {
            datas = scoreMapper.selectByjobnumber(course.getCoursenumber());
            for (Integer data : datas) {
                Student student = studentMapper.selectByPrimaryKey(data);
                student.setCoursenumber(course.getCoursenumber());
                student.setCoursename(course.getCoursename());
                student.setCoursecategory(course.getCoursecategory());
                ScoreKey scoreKey = new ScoreKey();
                scoreKey.setStuid(data);
                scoreKey.setCoursenumber(course.getCoursenumber());
                student.setScore(scoreMapper.selectByPrimaryKey(scoreKey));
                students.add(student);
            }
        }
        for (Student studentList2 : students) {
            if (student1.getStuId() == studentList2.getStuId() || student1.getStuname().equals(studentList2.getStuname())) {
                students2.add(studentList2);
            }
        }
        PageInfo<Student> studentList = new PageInfo<>(students2);
        return studentList;
    }

    @Override
    public AjaxResult insertScoreCourseNumber(@Param("stuid") Integer stuid, Integer coursenumber) {
        AjaxResult result = new AjaxResult();
        scoreMapper.insertScoreCourseNumber(stuid,coursenumber);
        result.setStatus(true);
        return result;
    }
}
