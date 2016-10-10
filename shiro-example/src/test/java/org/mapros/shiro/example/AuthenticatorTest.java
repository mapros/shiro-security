package org.mapros.shiro.example;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by HP on 2016/10/10.
 *
 * @author mapros
 */
public class AuthenticatorTest {
    @Test
    public void testAllSuccessfulStrategy() throws Exception {
        doLogin("classpath:shiro.authenticator.all.success.ini", "mapros", "984138");
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        Assert.assertEquals(2, principals.asList().size());
    }

    @Test(expected = org.apache.shiro.authc.UnknownAccountException.class)
    public void testAllFailedStrategy() throws Exception {
        doLogin("classpath:shiro.authenticator.all.failed.ini", "mapros", "984138");
    }

    @Test
    public void testFirstSuccessfulStrategy() throws Exception {
        doLogin("classpath:shiro.authenticator.first.success.ini", "mapros", "984138");
        Assert.assertEquals(1, SecurityUtils.getSubject().getPrincipals().asList().size());
    }

    @Test
    public void testAtLeastOneSuccessfulStrategy() throws Exception {
        doLogin("classpath:shiro.authenticator.atLeastOne.success.ini", "mapros", "984138");
        Assert.assertEquals(2, SecurityUtils.getSubject().getPrincipals().asList().size());
    }

    private void doLogin(String ini, String username, String password) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(ini);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
    }

    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();
    }
}
