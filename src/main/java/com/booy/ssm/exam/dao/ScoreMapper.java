package com.booy.ssm.exam.dao;

import com.booy.ssm.exam.pojo.Course;
import com.booy.ssm.exam.pojo.Score;
import com.booy.ssm.exam.pojo.ScoreKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScoreMapper {

    int deleteByPrimaryKey(ScoreKey key);

    int insert(Score record);

    int insertSelective(Score record);

    Integer selectByPrimaryKey(ScoreKey key);

    int updateByPrimaryKeySelective(Score score);

    int updateByPrimaryKey(Score record);

    List<Score> getScoretList(Score score, @Param("stuid") int stuid);

    List<Course> getCourse(@Param("jobnumber") Integer jobnumber);

    List<Integer> selectByjobnumber(int coursenumber);

    Integer findscorebyStuId(Integer stuId);

    void insertScoreCourseNumber(@Param("stuid") Integer stuid, @Param("coursenumber") Integer coursenumber);
}