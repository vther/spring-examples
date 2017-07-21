package com.vther.example1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:example1/teacher.xml"})
public class TeacherTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test() {
        Map<String, Teacher> beansOfType = applicationContext.getBeansOfType(Teacher.class);
        System.out.println("------------");
        System.out.println("------------");
        for (String key : beansOfType.keySet()) {
            System.out.println("key= " + key + " and value= " + beansOfType.get(key));
        }
        System.out.println("------------");
        System.out.println("------------");
    }
}