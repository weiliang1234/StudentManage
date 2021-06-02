package com.booy.ssm.exam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Course extends Score{
    private Integer coursenumber;
    private String coursename;
    private String coursecategory;
    private Integer classhours;
    private Integer credit;
    private Integer teacher;
    private String teachername;
    private Integer total = 100;
    private Integer yixuan;
    private Integer shengyu;
}