package cn.itrip.auth.test;

import cn.itrip.auth.service.UserService;
import cn.itrip.beans.pojo.ItripUser;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {
    @Test
    public void testInsertUser() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
        UserService userService = (UserService)ctx.getBean("userService");
        ItripUser user = new ItripUser();
        user.setUserCode("iphone52@aliyun.com");
        user.setUserName("文杰小哥哥");
        try {
            userService.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
