package com.booy.ssm.exam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private Integer jobnumber;
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private String department;
    private String position;
    private String education;
    private String nativeplace;
    private Date admissiondate;
    private String dateString;
    private String phone;
}