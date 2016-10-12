package org.mapros.shiro.ini;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by HP on 2016/10/12.
 *
 * @author mapros
 */
public class IniConfigurationTest {
    @Test
    public void testCreateWithIniConfiguration() throws Exception {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("mapros", "984138");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
    }
    @Test
    public void testCreateWithIniMainConfiguration() throws Exception {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.main.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("mapros", "984138");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
    }
}
