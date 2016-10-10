package org.mapros.shiro.example;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
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

    @Test
    public void testCustomizedRealm() throws Exception {
        //1.使用ini文件创建SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.customized.realm.ini");
        //2.通过工厂获取SecurityManager实例
        SecurityManager securityManager = factory.getInstance();
        //3.将获取到的securityManager实例绑定到SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
        //4.获取Subject实例
        Subject subject = SecurityUtils.getSubject();
        //5.创建UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken("mapros", "984138");
        //6.登录认证
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            System.out.println("[ This is a unknown account ! ]");
        } catch (IncorrectCredentialsException ice) {
            System.out.println("[ Error password ! ]");
        }
        //7.断言
        Assert.assertEquals(true, subject.isAuthenticated());
        //8.断言成功后退出登录
        subject.logout();
        System.out.println("[ Log out success ! ]");
    }

    @Test
    public void testMultiRealm() throws Exception {
        //1.使用ini文件创建SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.multi.realm.ini");
        //2.通过工厂获取SecurityManager实例
        SecurityManager securityManager = factory.getInstance();
        //3.将获取到的securityManager实例绑定到SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
        //4.获取Subject实例
        Subject subject = SecurityUtils.getSubject();
        //5.创建UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken("system", "123456");
        //6.登录认证
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            System.out.println("[ This is a unknown account ! ]");
        } catch (IncorrectCredentialsException ice) {
            System.out.println("[ Error password ! ]");
        }
        //7.断言
        Assert.assertEquals(true, subject.isAuthenticated());
        //8.断言成功后退出登录
        subject.logout();
        System.out.println("[ Log out success ! ]");
    }
}
