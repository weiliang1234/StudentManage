package com.booy.ssm.exam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudentLogin {
    private Integer stuId;
    private String stuname;
    private String password;
    private Integer flag;
    private String salt;

}