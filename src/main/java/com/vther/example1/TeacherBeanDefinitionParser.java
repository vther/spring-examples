package com.vther.example1;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * 注入单个Bean
 */
public class TeacherBeanDefinitionParser implements BeanDefinitionParser {


    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        if (!parserContext.getRegistry().containsBeanDefinition("teacher1")) {
            RootBeanDefinition def = new RootBeanDefinition();
            def.setBeanClass(Teacher.class);
            //def.setBeanClassName(Teacher.class.getCanonicalName());
            def.getPropertyValues().add("course", "chinese");
            def.getPropertyValues().add("name", "Mr Chen");
            def.getConstructorArgumentValues().addIndexedArgumentValue(0, "chinese");
            def.getConstructorArgumentValues().addIndexedArgumentValue(1, "Mr Chen");
            parserContext.registerBeanComponent(new BeanComponentDefinition(def, "teacher1"));
        }

        parserContext.getRegistry().
                registerBeanDefinition("teacher2",
                        BeanDefinitionBuilder.genericBeanDefinition(Teacher.class.getCanonicalName())
                                .addDependsOn("teacher3")
                                .setLazyInit(true)
                                .setScope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
                                .setInitMethodName("init")
                                .setDestroyMethodName("destroy")
                                .setRole(BeanDefinition.ROLE_INFRASTRUCTURE)
                                .getBeanDefinition());

        parserContext.getRegistry().registerBeanDefinition("stringBean1",
                BeanDefinitionBuilder.genericBeanDefinition(String.class)
                        .addConstructorArgValue("Mr Green")
                        .getBeanDefinition());

        parserContext.getRegistry().
                registerBeanDefinition("teacher3",
                        BeanDefinitionBuilder.genericBeanDefinition(Teacher.class)
                                .addPropertyValue("course", "english")
                                .addPropertyReference("name", "stringBean1")
                                .getBeanDefinition());

        parserContext.getRegistry().registerBeanDefinition("stringBean2",
                BeanDefinitionBuilder.genericBeanDefinition(String.class)
                        .addConstructorArgValue("Mr Wang")
                        .getBeanDefinition());
        parserContext.getRegistry().
                registerBeanDefinition("teacher4",
                        BeanDefinitionBuilder.genericBeanDefinition(Teacher.class)
                                .addConstructorArgValue("music")
                                .addConstructorArgReference("stringBean2")
                                .getBeanDefinition());
        return null;
    }


}