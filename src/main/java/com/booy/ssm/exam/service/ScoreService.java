package com.booy.ssm.exam.service;

import com.booy.ssm.exam.pojo.Course;
import com.booy.ssm.exam.pojo.Score;
import com.booy.ssm.exam.pojo.Student;
import com.booy.ssm.exam.utils.AjaxResult;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author:wl
 * @Date:2021/5/15 16:38
 * @projectName:ssmdemo
 * @description:
 */
public interface ScoreService {
    PageInfo<Course> getScoreList(Score score, int page, int limit,@Param("stuid") int stuid);

    PageInfo<Student> getScoreList2(int page, int limit, @Param("jobnumber") Integer jobnumber);

    Integer findscorebyStuId(Integer stuid);

    AjaxResult addScore(Score score);

    PageInfo<Student> getScoreList3(int page, int limit, @Param("jobnumber") Integer jobnumber, Student student);

    AjaxResult insertScoreCourseNumber(@Param("stuid") Integer stuId, Integer coursenumber);
}
