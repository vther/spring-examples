package com.vther.example1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:example1/extensible-xml-authoring.xml"})
public class StudentTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test() {
        Student student = (Student) applicationContext.getBean("student");
        Assert.assertNotNull(student);
        System.out.println(student);
    }
}