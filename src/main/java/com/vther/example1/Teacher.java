package com.vther.example1;

import lombok.Data;

@Data
public class Teacher {
    // 老师教的课程
    private String course;
    private String name;

    public Teacher() {
    }

    public Teacher(String course, String name) {
        this.course = course;
        this.name = name;
    }

    public void init() {
        System.out.println("------------------------ init");
    }

    public void destroy() {
        System.out.println("------------------------ destroy");
    }
}
