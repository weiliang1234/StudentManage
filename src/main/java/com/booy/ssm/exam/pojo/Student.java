package com.booy.ssm.exam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Course{
    private Integer stuId;
    private String stumajor;
    private String stuname;
    private String stusex;
    private Integer stuage;
    private String stuadress;
    private String phone;
    private String status;
    private String Salt;
    private Integer flag;
}