package org.mapros.shiro.example;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by HP on 2016/10/10.
 *
 * @author mapros
 */
public class LoginLogoutTest {
    @Test
    public void testLoginLogout() throws Exception {
        //1.使用ini配置文件创建SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.通过工厂获取一个SecurityManager的实例
        SecurityManager securityManager = factory.getInstance();
        //3.将获取到的SecurityManager实例绑定到SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
        //4.获取subject实例
        Subject subject = SecurityUtils.getSubject();
        //5.创建一个UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken("mapros", "984138");
        //6.将token交给subject进行登录认证
        subject.login(token);
        //7.断言用户已经登录
        Assert.assertEquals(true, subject.isAuthenticated());
        //8.退出
        subject.logout();
    }
}
