package org.mapros.shiro.authority;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by HP on 2016/10/11.
 *
 * @author mapros
 */
public class AuthorizerTest extends BaseTest {
    @Test
    public void testIsPermitted() throws Exception {
        doLogin("classpath:shiro.authorizer.ini", "admin", "12345");
        Assert.assertTrue(subject().isPermitted("user1:update"));
        Assert.assertTrue(subject().isPermitted("user2:update"));
        Assert.assertTrue(subject().isPermitted("+user1+2"));
        Assert.assertTrue(subject().isPermitted("+user1+8"));
        Assert.assertTrue(subject().isPermitted("+user2+10"));
        Assert.assertFalse(subject().isPermitted("+user1+4"));
        Assert.assertTrue(subject().isPermitted("menu:view"));
    }
}
