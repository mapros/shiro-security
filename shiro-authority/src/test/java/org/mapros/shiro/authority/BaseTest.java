package org.mapros.shiro.authority;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by HP on 2016/10/11.
 *
 * @author mapros
 */
public abstract class BaseTest {
    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//clear thread bind of subject
    }

    protected void doLogin(String ini, String username, String password) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(ini);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(username, password));
    }

    protected Subject subject() {
        return SecurityUtils.getSubject();
    }
}
