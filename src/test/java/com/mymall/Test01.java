package com.mymall;

import com.mymall.service.TestService;
import org.apache.ibatis.io.Resources;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {

    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TestService bean = context.getBean(TestService.class);
        bean.test();
    }
}
