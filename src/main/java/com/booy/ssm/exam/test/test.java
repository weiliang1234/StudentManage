package com.booy.ssm.exam.test;

import com.booy.ssm.exam.dao.UserDAO;
import com.booy.ssm.exam.pojo.User;
import com.booy.ssm.exam.utils.DataUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author:wl
 * @Date:2021/2/11 11:19
 * @projectName:ssm03
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class test {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void user() {
        User user = userDAO.getUserByAccount("a000001");
        System.out.println(user);
    }

    @Test
    public void date() {
        System.out.println(DataUtils.SimpleDateFormat("2020-11-1"));
        System.out.println(DataUtils.format(new Date()));
    }

    @Test
    public void wl() {
        int a, b;
        for(a=1,b=1;a<100;a++){
            if(b>=20)
                break;
            if(b % 3 ==1){
                b=b+3;
                continue;
            }
            b=b-5;
        }
        System.out.println(a);
    }
}
