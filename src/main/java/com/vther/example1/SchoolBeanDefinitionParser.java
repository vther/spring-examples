package com.vther.example1;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SchoolBeanDefinitionParser extends AbstractBeanDefinitionParser {

    private static final String META_DATA_ELEMENT = "meta-data";

    private static final String SELECTORS_ELEMENT = "selectors";
    private static final String SELECTOR_PACKAGE_ELEMENT = "package";

    private static final String SELECTOR_SUFFIX = "$selector";
    private static final String CHECK_SUPERCLASS_ATTRIBUTE = "check-superclass";

    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        Element innerBeanElement = DomUtils.getChildElementByTagName(element,
                BeanDefinitionParserDelegate.BEAN_ELEMENT);
        AbstractBeanDefinition school;
        if (innerBeanElement != null) {
            // 这种方式可以解析Bean注入的
            school = parserContext.getDelegate().parseBeanDefinitionElement(innerBeanElement, null, null);
        } else {
            // 这种方式可以解析属性输入的
            school = new GenericBeanDefinition();
            school.setBeanClass(School.class);
            school.getPropertyValues().addPropertyValue("address", element.getAttribute("address"));
            school.getPropertyValues().addPropertyValue("name", element.getAttribute("name"));
        }
        Map metaData = parseMetaData(element, parserContext, null);
        school.getPropertyValues().addPropertyValue("metaData", metaData);

        List selectors = parseSelectors(element);
        school.getPropertyValues().addPropertyValue("selectors", selectors);

        return school;
    }

    /**
     * 是否由spring自动生成Bean的ID，
     * -- 如果返回true，则由spring框架生成ID，生成的ID形如 'com.vther.example1.School#0'
     * -- 如果返回false，则必须在xsd里面为bean配置ID属性，且给Bean配置上ID
     * ------ 返回false，且不配置ID会抛出异常： Id is required for element 'school' when used as a top-level tag
     *
     * @return 是否由spring自动生成Bean的ID
     */
    @Override
    protected boolean shouldGenerateId() {
        return false;
    }

    private Map parseMetaData(Element element, ParserContext parserContext, AbstractBeanDefinition beanDefinition) {
        Element metaDataElement = DomUtils.getChildElementByTagName(element, META_DATA_ELEMENT);
        if (metaDataElement == null) {
            return new ManagedMap();
        }
        return parserContext.getDelegate().parseMapElement(metaDataElement, beanDefinition);
    }

    private List parseSelectors(Element element) {
        Element selectorsElement = DomUtils.getChildElementByTagName(element, SELECTORS_ELEMENT);
        List<String> selectorStrList = new ArrayList<>();
        if (selectorsElement != null) {
            List<Element> selectors = DomUtils.getChildElements(selectorsElement);
            for (Element child : selectors) {
                String localName = child.getLocalName();
                if (localName.equals("package")) {
                    selectorStrList.add(child.getAttribute("prefix"));
                } else if (localName.equals("class-name-matches")) {
                    selectorStrList.add(child.getAttribute("pattern"));
                }
            }
        }
        return selectorStrList;
    }
}