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
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("mapros", "984138");
        subject.login(token);
        Assert.assertEquals(true, subject.isAuthenticated());
        subject.logout();
    }
}
