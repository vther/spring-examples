package com.vther.example1;


import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class VtherNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("student", new StudentBeanDefinitionParser());
        registerBeanDefinitionParser("teacher", new TeacherBeanDefinitionParser());
        registerBeanDefinitionParser("school", new SchoolBeanDefinitionParser());
    }

}