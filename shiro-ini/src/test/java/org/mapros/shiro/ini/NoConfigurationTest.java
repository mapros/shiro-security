package org.mapros.shiro.ini;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

/**
 * Created by HP on 2016/10/12.
 *
 * @author mapros
 */
public class NoConfigurationTest {
    @Test
    public void testCreateWithoutConfiguration() throws Exception {
        //create a default security manager
        DefaultSecurityManager manager = new DefaultSecurityManager();
        //set authenticator
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        manager.setAuthenticator(authenticator);
        //set authorizer
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
        authorizer.setPermissionResolver(new WildcardPermissionResolver());
        manager.setAuthorizer(authorizer);
        //set realm
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///shiro");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        JdbcRealm realm = new JdbcRealm();
        realm.setDataSource(dataSource);
        realm.setPermissionsLookupEnabled(true);
        manager.setRealms(Collections.singletonList((Realm) realm));
        //bind manager to SecurityUtils
        SecurityUtils.setSecurityManager(manager);
        //get subject
        Subject subject = SecurityUtils.getSubject();
        //create token and do login
        UsernamePasswordToken token = new UsernamePasswordToken("mapros", "984138");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
    }
}
